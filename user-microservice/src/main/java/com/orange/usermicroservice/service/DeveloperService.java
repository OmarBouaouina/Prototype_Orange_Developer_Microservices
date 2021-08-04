package com.orange.usermicroservice.service;

import com.orange.usermicroservice.model.dto.DeveloperCreationDTO;
import com.orange.usermicroservice.model.dto.DeveloperListDTO;
import com.orange.usermicroservice.model.dto.DeveloperPostOperationDTO;
import com.orange.usermicroservice.model.dto.DeveloperUpdateDTO;

import java.util.List;

public interface DeveloperService {
    List<DeveloperListDTO> getAllDevelopers();

    DeveloperPostOperationDTO createDeveloper(DeveloperCreationDTO developer) throws Exception;

    DeveloperPostOperationDTO deleteDeveloper(String id);

    DeveloperPostOperationDTO getDeveloperByCuid(String cuid);

    DeveloperPostOperationDTO updateDeveloper(String cuid, DeveloperUpdateDTO developerUpdateDTO);

    void deleteAllDevelopers();
}
