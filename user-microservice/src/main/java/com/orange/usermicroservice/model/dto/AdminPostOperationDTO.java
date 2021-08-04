package com.orange.usermicroservice.model.dto;

import com.orange.usermicroservice.model.entity.AdminEntity;
import com.orange.usermicroservice.model.entity.OfferEntity;
import com.orange.usermicroservice.model.enums.AdminRole;
import com.orange.usermicroservice.model.enums.UserStatus;
import com.orange.usermicroservice.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AdminPostOperationDTO {

    private String id;

    private String naturalId;

    private String email;

    private String firstName;

    private String lastName;

    private UserStatus userStatus;

    private UserType userType;

    private String applicationName;

    private boolean isApiFactoryMember;

    private boolean isModified;

    private Set<AdminRole> roles = new HashSet<>();

    private Set<String> permissions = new HashSet<>();

    private Set<OfferEntity> ownedOffers;

    public AdminPostOperationDTO(AdminEntity admin){
        this.id = admin.getId();
        this.naturalId = admin.getNaturalId();
        this.email = admin.getEmail();
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.userStatus = admin.getUserStatus();
        this.userType = admin.getUserType();
        this.applicationName = admin.getApplicationName();
        this.isApiFactoryMember = admin.isApiFactoryMember();
        this.isModified = admin.isModified();
        this.roles = admin.getRoles();
        this.permissions = admin.getUserPermissions();
        this.ownedOffers = admin.getOffers();
    }
}