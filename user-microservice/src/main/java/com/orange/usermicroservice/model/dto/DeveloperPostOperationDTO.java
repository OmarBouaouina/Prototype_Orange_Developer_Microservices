package com.orange.usermicroservice.model.dto;

import com.orange.usermicroservice.model.entity.DeveloperEntity;
import com.orange.usermicroservice.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class DeveloperPostOperationDTO {

    private String id;

    private String cuid;

    private String email;

    private String firstName;

    private String lastName;

    private UserType userType;

    private Set<String> permissions = new HashSet<>();

    public DeveloperPostOperationDTO(DeveloperEntity developer) {
        this.id = developer.getId();
        this.cuid = developer.getCuid();
        this.email = developer.getEmail();
        this.firstName = developer.getFirstName();
        this.lastName = developer.getLastName();
        this.userType = developer.getUserType();
        this.permissions = developer.getUserPermissions();
    }
}
