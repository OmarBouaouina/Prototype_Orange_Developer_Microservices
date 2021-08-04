package com.orange.usermicroservice.model.dto;

import com.orange.usermicroservice.model.enums.AdminRole;
import com.orange.usermicroservice.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AdminProducedDTO {

    private String id;

    private String naturalId;

    private String email;

    private UserType userType;

    @Enumerated(value = EnumType.STRING)
    private Set<AdminRole> roles = new HashSet<>();

    private Set<String> permissions = new HashSet<>();

    public AdminProducedDTO(AdminPostOperationDTO adminPostOperationDTO){
        this.id = adminPostOperationDTO.getId();
        this.naturalId = adminPostOperationDTO.getNaturalId();
        this.email = adminPostOperationDTO.getEmail();
        this.userType = adminPostOperationDTO.getUserType();
        this.roles = adminPostOperationDTO.getRoles();
        this.permissions = adminPostOperationDTO.getPermissions();
    }
}
