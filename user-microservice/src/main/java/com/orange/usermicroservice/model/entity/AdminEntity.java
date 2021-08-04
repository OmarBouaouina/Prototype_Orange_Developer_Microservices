package com.orange.usermicroservice.model.entity;

import com.orange.usermicroservice.custom.CuidGenerator;
import com.orange.usermicroservice.model.constant.AssignPermission;
import com.orange.usermicroservice.model.dto.AdminCreationDTO;
import com.orange.usermicroservice.model.enums.AdminRole;
import com.orange.usermicroservice.model.enums.UserRole;
import com.orange.usermicroservice.model.enums.UserStatus;
import com.orange.usermicroservice.model.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admin")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminEntity extends UserEntity {

    @Column(name = "natural_id", unique = true)
    private String naturalId;

    @Column(name = "api_factory_member")
    private boolean isApiFactoryMember;

    @Column(name = "is_modified")
    private boolean isModified;

    @ElementCollection(targetClass = AdminRole.class)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "roles")
    private Set<AdminRole> roles = new HashSet<>();

    @ManyToMany(mappedBy = "owners", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<OfferEntity> offers = new HashSet<>();

    public AdminEntity(String email, String password, UserType userType, String applicationName, String firstName, String lastName, boolean isApiFactoryMember, Set<AdminRole> roles) throws Exception {
        super(email, password, new Date(System.currentTimeMillis()), UserRole.ROLE_ADMINISTRATOR, UserStatus.ENABLED, userType, applicationName, firstName, lastName);
        this.naturalId = CuidGenerator.generateId();
        this.isApiFactoryMember = isApiFactoryMember;
        this.isModified = false;
        this.roles = roles;
        for(AdminRole role : roles){
            this.addPermissions(AssignPermission.assignPermissions(role));
        }
    }

    public AdminEntity(AdminCreationDTO adminToCreate) throws Exception {
        super(adminToCreate.getEmail(), adminToCreate.getPassword(), new Date(System.currentTimeMillis()), UserRole.ROLE_ADMINISTRATOR, UserStatus.ENABLED, adminToCreate.getUserType(), adminToCreate.getApplicationName(), adminToCreate.getFirstName(), adminToCreate.getLastName());
        this.naturalId = CuidGenerator.generateId();
        this.isApiFactoryMember = adminToCreate.isApiFactoryMember();
        this.isModified = false;
        this.roles = adminToCreate.getRoles();
        for(AdminRole role : adminToCreate.getRoles()){
            this.addPermissions(AssignPermission.assignPermissions(role));
        }
    }

    public void addRole(AdminRole role){
        if(role != null) {
            roles.add(role);
            addPermissions(AssignPermission.assignPermissions(role));
        }
    }

    public void removeRole(AdminRole role){
        if(role != null){
            if(roles.contains(role)){
                roles.remove(role);
            }
        }
    }

    public void addOffer(OfferEntity offer){
        if(offer != null){
            if(!offers.contains(offer)){
                offers.add(offer);
            }
        }
    }

    public void removeOffer(OfferEntity offer){
        if(offer != null){
            if(offers.contains(offer)){
                offers.remove(offer);
            }
        }
    }
}