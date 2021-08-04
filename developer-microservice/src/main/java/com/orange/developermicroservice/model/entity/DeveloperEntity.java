package com.orange.developermicroservice.model.entity;

import com.orange.developermicroservice.model.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "developer")
@ToString
@Getter
@Setter
@NoArgsConstructor
public class DeveloperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;

    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "cuid", unique = true)
    private String cuid;

    @Column(name = "email", unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private UserType userType;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> permissions = new HashSet<>();

    public DeveloperEntity(String id, String cuid, String email, UserType userType, Set<String> permissions) {
        this.id = id;
        this.cuid = cuid;
        this.email = email;
        this.userType = userType;
        this.permissions = permissions;
    }
}
