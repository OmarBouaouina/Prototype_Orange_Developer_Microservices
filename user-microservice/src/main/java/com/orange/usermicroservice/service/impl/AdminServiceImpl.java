package com.orange.usermicroservice.service.impl;

import com.orange.usermicroservice.model.constant.AssignPermission;
import com.orange.usermicroservice.model.dto.AdminCreationDTO;
import com.orange.usermicroservice.model.dto.AdminListDTO;
import com.orange.usermicroservice.model.dto.AdminPostOperationDTO;
import com.orange.usermicroservice.model.dto.AdminUpdateDTO;
import com.orange.usermicroservice.model.entity.AdminEntity;
import com.orange.usermicroservice.model.entity.UserEntity;
import com.orange.usermicroservice.model.enums.AdminRole;
import com.orange.usermicroservice.repository.AdminRepository;
import com.orange.usermicroservice.service.AdminService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@NoArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Set<String> getAllAdminPermissions() {
        Set<String> allAdminPermissions = new HashSet<>();
        List<AdminEntity> savedAdmins = adminRepository.findAll();
        for(AdminEntity admin : savedAdmins){
            allAdminPermissions.addAll(admin.getUserPermissions());
        }
        return allAdminPermissions;
    }

    @Override
    public List<AdminListDTO> getAllAdmins() {
        List<AdminEntity> savedAdmins = adminRepository.findAll();
        if(savedAdmins != null){
            List<AdminListDTO> allAdmins = new ArrayList<>();
            for(AdminEntity admin : savedAdmins){
                allAdmins.add(new AdminListDTO(admin));
            }
            return allAdmins;
        }
        return null;
    }

    @Override
    public AdminPostOperationDTO getAdminById(String naturalId) {
        AdminEntity savedAdmin = adminRepository.findAdminByNaturalId(naturalId);
        if(savedAdmin != null) {
            return new AdminPostOperationDTO(savedAdmin);
        }
        return null;
    }

    @Override
    public AdminPostOperationDTO createAdmin(AdminCreationDTO adminToCreate) throws Exception {
        AdminEntity adminToSave = new AdminEntity(adminToCreate);
        adminRepository.save(adminToSave);
        AdminPostOperationDTO createdAdmin = new AdminPostOperationDTO(adminToSave);
        return createdAdmin;
    }

    @Override
    public AdminPostOperationDTO deleteAdmin(String naturalId) {
        AdminEntity adminToDelete = adminRepository.findAdminByNaturalId(naturalId);
        if(adminToDelete != null) {
            AdminPostOperationDTO deletedAdmin = new AdminPostOperationDTO(adminToDelete);
            adminRepository.delete(adminToDelete);
            return deletedAdmin;
        } else {
            return null;
        }
    }

    @Override
    public AdminPostOperationDTO updateAdmin(String naturalId, AdminUpdateDTO adminUpdateDTO) {
        AdminEntity adminToUpdate = adminRepository.findAdminByNaturalId(naturalId);
        if(adminToUpdate != null){
            if(adminUpdateDTO.getEmail() != null && adminUpdateDTO.getEmail().length() != 0) { adminToUpdate.setEmail(adminUpdateDTO.getEmail()); }
            if(adminUpdateDTO.getPassword() != null && adminUpdateDTO.getPassword().length() != 0) { adminToUpdate.setPassword(EncryptService.encrypt(adminUpdateDTO.getPassword())); }
            adminToUpdate.setUserStatus(adminUpdateDTO.getUserStatus());
            adminToUpdate.setApiFactoryMember(adminUpdateDTO.isApiFactoryMember());
            adminToUpdate.setModified(adminUpdateDTO.isModified());
            if(adminUpdateDTO.getRoles() != null) { adminToUpdate.setRoles(adminUpdateDTO.getRoles()); }
            for(AdminRole role : adminUpdateDTO.getRoles()){
                adminToUpdate.addPermissions(AssignPermission.assignPermissions(role));
            }
            adminRepository.save(adminToUpdate);
            return new AdminPostOperationDTO(adminToUpdate);
        }
        return null;
    }

    @Override
    public Set<AdminRole> getAllAdminRoles() {
        Set<AdminRole> allAdminRoles = new HashSet<>();
        List<AdminListDTO> adminsList = getAllAdmins();
        for (AdminListDTO admin : adminsList){
            allAdminRoles.addAll(admin.getRoles());
        }
        return allAdminRoles;
    }

//    @Override
//    public AdminPostOperationDTO getConnectedAdmin() {
//        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getDetails();
//        AdminPostOperationDTO admin = new AdminPostOperationDTO(adminRepository.findAdminByEmail(user.getEmail()));
//        if(admin == null){
//            return null;
//        }
//        return admin;
//    }
//
//    @Override
//    public Set<AdminRole> getManagedRoles() {
//        return getConnectedAdmin().getRoles();
//    }
//
//    @Override
//    public AdminPostOperationDTO addSelfPublisherRole() {
//        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getDetails();
//        AdminEntity admin = adminRepository.findAdminByEmail(user.getEmail());
//        if(admin != null){
//            admin.addRole(AdminRole.SELF_PUBLISHER);
//            adminRepository.save(admin);
//            return new AdminPostOperationDTO(admin);
//        }
//        return null;
//    }
}