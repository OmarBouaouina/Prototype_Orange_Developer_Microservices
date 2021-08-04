package com.orange.usermicroservice.model.dto;

import com.orange.usermicroservice.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeveloperCreationDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private UserType userType;

    private String applicationName;

    private String country;

    private String job;

    private String companyName;

    private String companyActivity;

    private boolean isNewsLetterMember;
}
