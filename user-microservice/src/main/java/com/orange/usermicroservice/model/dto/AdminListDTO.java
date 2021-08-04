package com.orange.usermicroservice.model.dto;

import com.orange.usermicroservice.model.entity.AdminEntity;
import com.orange.usermicroservice.model.enums.AdminRole;
import com.orange.usermicroservice.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AdminListDTO {

    private String id;

    private String naturalId;

    private String email;

    private UserType userType;

    private Set<AdminRole> roles;

    public AdminListDTO(AdminEntity admin){
        this.id = admin.getId();
        this.naturalId = admin.getNaturalId();
        this.email = admin.getEmail();
        this.userType = admin.getUserType();
        this.roles = admin.getRoles();
    }
}
