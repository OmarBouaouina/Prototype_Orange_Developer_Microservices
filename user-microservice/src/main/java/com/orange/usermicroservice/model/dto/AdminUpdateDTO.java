package com.orange.usermicroservice.model.dto;

import com.orange.usermicroservice.model.enums.AdminRole;
import com.orange.usermicroservice.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class AdminUpdateDTO {

    private String email;

    private String password;

    private UserStatus userStatus;

    private boolean isApiFactoryMember;

    private boolean isModified;

    private Set<AdminRole> roles;
}
