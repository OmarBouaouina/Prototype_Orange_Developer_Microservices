package com.orange.developermicroservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DeveloperUpdatedDTO {

    private String cuid;

    private String email;

    private Set<String> permissions;
}
