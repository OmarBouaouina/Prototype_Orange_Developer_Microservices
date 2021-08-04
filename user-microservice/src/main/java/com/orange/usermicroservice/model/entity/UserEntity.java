package com.orange.usermicroservice.model.entity;

import com.orange.usermicroservice.custom.IdGenerator;
import com.orange.usermicroservice.model.enums.UserRole;
import com.orange.usermicroservice.model.enums.UserStatus;
import com.orange.usermicroservice.model.enums.UserType;
import com.orange.usermicroservice.service.impl.EncryptService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@NoArgsConstructor
@ToString
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;

    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "login", unique = true)
    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(name = "creation_date")
    private Date creationDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private UserStatus userStatus;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private UserType userType;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> userPermissions = new HashSet<>();

    @Column(name = "application_name")
    private String applicationName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public UserEntity(String email, String password, Date creationDate, UserRole userRole, UserStatus userStatus, UserType userType, Set<String> userPermissions, String applicationName, String firstName, String lastName) throws Exception {
        if(userType == UserType.MACHINE){
            if(applicationName != null && applicationName.length() != 0){
                this.id = IdGenerator.generateId();
                this.email = email;
                this.password = EncryptService.encrypt(password);
                this.creationDate = creationDate;
                this.userRole = userRole;
                this.userStatus = userStatus;
                this.userType = userType;
                this.userPermissions = userPermissions;
                this.applicationName = applicationName;
                this.firstName = null;
                this.lastName = null;
            } else{
                throw new Exception("Application name should not be null or empty, for a machine user.");
            }
        } else{
            if(firstName != null && firstName.length() != 0 && lastName != null && lastName.length() != 0){
                this.id = IdGenerator.generateId();
                this.email = email;
                this.password = EncryptService.encrypt(password);
                this.creationDate = creationDate;
                this.userRole = userRole;
                this.userStatus = userStatus;
                this.userType = userType;
                this.userPermissions = userPermissions;
                this.applicationName = null;
                this.firstName = firstName;
                this.lastName = lastName;
            } else {
                throw new Exception("First/Last name should not be null or empty, for a human user.");
            }
        }
    }

    public UserEntity(String email, String password, Date creationDate, UserRole userRole, UserStatus userStatus, UserType userType, String applicationName, String firstName, String lastName) throws Exception {
        if(userType == UserType.MACHINE){
            if(applicationName != null && applicationName.length() != 0){
                this.id = IdGenerator.generateId();
                this.email = email;
                this.password = EncryptService.encrypt(password);
                this.creationDate = creationDate;
                this.userRole = userRole;
                this.userStatus = userStatus;
                this.userType = userType;
                this.applicationName = applicationName;
                this.firstName = firstName;
                this.lastName = lastName;
            } else{
                throw new Exception("Application name should not be null or empty, for a machine user.");
            }
        } else{
            this.id = IdGenerator.generateId();
            this.email = email;
            this.password = EncryptService.encrypt(password);
            this.creationDate = creationDate;
            this.userRole = userRole;
            this.userStatus = userStatus;
            this.userType = userType;
            this.applicationName = applicationName;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    public boolean hasPermission(String permission){
        return userPermissions.contains(permission);
    }

    public void addPermission(String permission){
        userPermissions.add(permission);
    }

    public void addPermissions(Set<String> permissions){
        userPermissions.addAll(permissions);
    }

    public void removePermission(String permission){
        if(hasPermission(permission)){
            userPermissions.remove(permission);
        }
    }
}
