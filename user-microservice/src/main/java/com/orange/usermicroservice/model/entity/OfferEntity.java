package com.orange.usermicroservice.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "offer")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;

    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name="offer_administrator", joinColumns = @JoinColumn(name="offer_internal_id"), inverseJoinColumns = @JoinColumn(name="administrator_internal_id"))
    private Set<AdminEntity> owners = new HashSet<>();

    public OfferEntity(String id, String name, Set<AdminEntity> owners) {
        this.id = id;
        this.name = name;
        this.owners = owners;
    }
}