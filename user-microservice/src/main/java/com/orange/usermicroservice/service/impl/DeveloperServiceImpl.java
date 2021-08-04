package com.orange.usermicroservice.service.impl;

import com.orange.usermicroservice.model.dto.DeveloperCreationDTO;
import com.orange.usermicroservice.model.dto.DeveloperListDTO;
import com.orange.usermicroservice.model.dto.DeveloperPostOperationDTO;
import com.orange.usermicroservice.model.dto.DeveloperUpdateDTO;
import com.orange.usermicroservice.model.entity.DeveloperEntity;
import com.orange.usermicroservice.repository.DeveloperRepository;
import com.orange.usermicroservice.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Override
    public List<DeveloperListDTO> getAllDevelopers() {
        List<DeveloperEntity> savedDevelopers = developerRepository.findAll();
        if(savedDevelopers != null){
            List<DeveloperListDTO> allDevelopers= new ArrayList<>();
            for(DeveloperEntity developer : savedDevelopers){
                allDevelopers.add(new DeveloperListDTO(developer));
            }
            return allDevelopers;
        }
        return null;
    }

    @Override
    public DeveloperPostOperationDTO getDeveloperByCuid(String cuid){
        DeveloperEntity savedDeveloper = developerRepository.findDeveloperByCuid(cuid);
        if(savedDeveloper != null) {
            return new DeveloperPostOperationDTO(savedDeveloper);
        }
        return null;
    }

    @Override
    public DeveloperPostOperationDTO updateDeveloper(String cuid, DeveloperUpdateDTO developerUpdateDTO) {
        DeveloperEntity developerToUpdate = developerRepository.findDeveloperByCuid(cuid);
        if(developerToUpdate != null){
            if(developerUpdateDTO.getEmail() != null && developerUpdateDTO.getEmail().length() != 0) { developerToUpdate.setEmail(developerUpdateDTO.getEmail()); }
            if(developerUpdateDTO.getPassword() != null && developerUpdateDTO.getPassword().length() !=0) { developerToUpdate.setPassword(EncryptService.encrypt(developerUpdateDTO.getPassword())); }
            if(developerUpdateDTO.getUserStatus() != null) { developerToUpdate.setUserStatus(developerUpdateDTO.getUserStatus()); }
            if(developerUpdateDTO.getCountry() != null && developerUpdateDTO.getCountry().length() != 0) { developerToUpdate.setCountry(developerUpdateDTO.getCountry()); }
            if(developerUpdateDTO.getJob() != null && developerUpdateDTO.getJob().length() != 0) { developerToUpdate.setJob(developerUpdateDTO.getJob()); }
            if(developerUpdateDTO.getCompanyName() != null && developerUpdateDTO.getCompanyName().length() != 0) { developerToUpdate.setCompanyName(developerUpdateDTO.getCompanyName()); }
            if(developerUpdateDTO.getCompanyActivity() != null && developerUpdateDTO.getCompanyActivity().length() != 0) { developerToUpdate.setCompanyActivity(developerUpdateDTO.getCompanyActivity()); }
            developerToUpdate.setNewsletterMember(developerUpdateDTO.isNewsLetterMember());
            if(developerUpdateDTO.getPermissions() != null && developerUpdateDTO.getPermissions().size() != 0) { developerToUpdate.setUserPermissions(developerUpdateDTO.getPermissions());}
            developerRepository.save(developerToUpdate);
            return new DeveloperPostOperationDTO(developerToUpdate);
        }
        return null;
    }

    @Override
    public void deleteAllDevelopers() {
        developerRepository.deleteAll();
    }

    @Override
    public DeveloperPostOperationDTO createDeveloper(DeveloperCreationDTO developerToCreate) throws Exception {
        DeveloperEntity developerToSave = new DeveloperEntity(developerToCreate);
        developerRepository.save(developerToSave);
        DeveloperPostOperationDTO createdDeveloper = new DeveloperPostOperationDTO(developerToSave);
        return createdDeveloper;
    }

    @Override
    public DeveloperPostOperationDTO deleteDeveloper(String cuid) {
        DeveloperEntity developerToDelete = developerRepository.findDeveloperByCuid(cuid);
        if(developerToDelete != null) {
            DeveloperPostOperationDTO deletedDeveloper = new DeveloperPostOperationDTO(developerToDelete);
            developerRepository.delete(developerToDelete);
            return deletedDeveloper;
        } else {
            return null;
        }
    }
}
