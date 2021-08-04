package com.orange.adminmicroservice.model.entity;

import com.orange.adminmicroservice.model.enums.AdminRole;
import com.orange.adminmicroservice.model.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admin")
@ToString
@Getter
@Setter
@NoArgsConstructor
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;

    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "natural_id", unique = true)
    private String naturalId;

    @Column(name = "email", unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private UserType userType;

    @ElementCollection(targetClass = AdminRole.class)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "roles")
    private Set<AdminRole> roles;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "owners", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EditableOfferEntity> offers;

    public AdminEntity(String id, String naturalId, String email, UserType userType, Set<AdminRole> roles, Set<String> permissions) {
        this.id = id;
        this.naturalId = naturalId;
        this.email = email;
        this.userType = userType;
        this.roles = roles;
        this.permissions = permissions;
    }
}
