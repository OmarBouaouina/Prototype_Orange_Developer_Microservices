package com.orange.usermicroservice.model.dto;

import com.orange.usermicroservice.model.enums.UserStatus;
import com.orange.usermicroservice.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class DeveloperUpdateDTO {

    private String email;

    private String password;

    private UserStatus userStatus;

    private String country;

    private String job;

    private String companyName;

    private String companyActivity;

    private boolean isNewsLetterMember;

    private Set<String> permissions;
}
