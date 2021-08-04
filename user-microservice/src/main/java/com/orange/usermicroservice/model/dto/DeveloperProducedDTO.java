package com.orange.usermicroservice.model.dto;

import com.orange.usermicroservice.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class DeveloperProducedDTO {

    private String id;

    private String cuid;

    private String email;

    private UserType userType;

    private Set<String> permissions = new HashSet<>();

    public DeveloperProducedDTO(DeveloperPostOperationDTO developerPostOperationDTO) {
        this.id = developerPostOperationDTO.getId();
        this.cuid = developerPostOperationDTO.getCuid();
        this.email = developerPostOperationDTO.getEmail();
        this.userType = developerPostOperationDTO.getUserType();
        this.permissions = developerPostOperationDTO.getPermissions();
    }
}
