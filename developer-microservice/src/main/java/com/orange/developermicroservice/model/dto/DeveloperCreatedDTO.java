package com.orange.developermicroservice.model.dto;

import com.orange.developermicroservice.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class DeveloperCreatedDTO {

    private String id;

    private String cuid;

    private String email;

    private UserType userType;

    private Set<String> permissions = new HashSet<>();
}
