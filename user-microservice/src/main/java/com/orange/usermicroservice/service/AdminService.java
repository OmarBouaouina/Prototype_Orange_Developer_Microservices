package com.orange.usermicroservice.service;

import com.orange.usermicroservice.model.dto.AdminCreationDTO;
import com.orange.usermicroservice.model.dto.AdminListDTO;
import com.orange.usermicroservice.model.dto.AdminPostOperationDTO;
import com.orange.usermicroservice.model.dto.AdminUpdateDTO;
import com.orange.usermicroservice.model.enums.AdminRole;

import java.util.List;
import java.util.Set;

public interface AdminService {

    List<AdminListDTO> getAllAdmins();

    AdminPostOperationDTO getAdminById(String naturalId);

    Set<AdminRole> getAllAdminRoles();

    AdminPostOperationDTO createAdmin(AdminCreationDTO admin) throws Exception;

    AdminPostOperationDTO deleteAdmin(String naturalId);

    AdminPostOperationDTO updateAdmin(String naturalId, AdminUpdateDTO adminUpdateDTO);

    Set<String> getAllAdminPermissions();

//    AdminPostOperationDTO getConnectedAdmin();
//
//    Set<AdminRole> getManagedRoles();
//
//    AdminPostOperationDTO addSelfPublisherRole();
}
