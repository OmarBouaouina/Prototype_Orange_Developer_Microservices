package com.orange.adminmicroservice.model.dto;

import com.orange.adminmicroservice.model.enums.AdminRole;
import com.orange.adminmicroservice.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AdminCreatedDTO {

    private String id;

    private String naturalId;

    private String email;

    private UserType userType;

    @Enumerated(value = EnumType.STRING)
    private Set<AdminRole> roles = new HashSet<>();

    private Set<String> permissions = new HashSet<>();
}
