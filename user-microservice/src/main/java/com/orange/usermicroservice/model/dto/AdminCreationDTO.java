package com.orange.usermicroservice.model.dto;

import com.orange.usermicroservice.model.enums.AdminRole;
import com.orange.usermicroservice.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class AdminCreationDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private UserType userType;

    private String applicationName;

    private boolean isApiFactoryMember;

    private Set<AdminRole> roles;
}
