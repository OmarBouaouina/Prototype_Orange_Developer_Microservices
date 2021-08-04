package com.orange.usermicroservice.model.dto;

import com.orange.usermicroservice.model.entity.DeveloperEntity;
import com.orange.usermicroservice.model.enums.UserType;

public class DeveloperListDTO {

    private String id;

    private String cuid;

    private String email;

    private UserType userType;

    public DeveloperListDTO(DeveloperEntity developer){
        this.id = developer.getId();
        this.cuid = developer.getCuid();
        this.email = developer.getEmail();
        this.userType = developer.getUserType();
    }
}
