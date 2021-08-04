package com.orange.adminmicroservice.model.entity;

import com.orange.adminmicroservice.model.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "editable_product")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EditableProductEntity extends EditableEntity {

    @Column(name = "api_management_id")
    private String apiManagementId;

    @Column(name = "icon_url")
    @URL
    private String iconUrl;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private ProductStatus status;

    @Column(name = "promoted")
    private boolean promoted;

    @Column(name = "restricted")
    private boolean restricted;

    @Column(name = "configurable")
    private boolean configurable;

    @Column(name = "configuration_url")
    @URL
    private String configurationUrl;

    @Column(name = "secret")
    private String secret;

    @Column(name = "version")
    private String version;

    @Column(name = "self_exposable")
    private boolean selfExposable;

    @Column(name = "provisionable")
    private boolean provisionable;

    @Column(name = "provisioning_url")
    @URL
    private String provisioningUrl;

    @Column(name = "updated_content")
    private boolean updatedContent;

    @ElementCollection
    @Column(name = "event")
    @CollectionTable(name = "provisioning_event", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> provisioningEvents = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "administrator_id")
    private AdminEntity administrator;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "creator_id")
    private AdminEntity creator;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "product_content", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "content_id")})
//    private List<EditableContentEntity> contents = new ArrayList<>();


}