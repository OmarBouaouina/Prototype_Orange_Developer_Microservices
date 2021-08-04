package com.orange.adminmicroservice.model.entity;

import com.orange.adminmicroservice.model.dto.CreateOfferDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "editable_offer")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EditableOfferEntity extends EditableEntity {

    @Column(name = "community_support_url")
    @URL
    private String commmunitySupportUrl;

    @Column(name = "publication_date")
    private Date publicationDate;

    @Column(name = "first_publication_date")
    private Date firstPublicationDate;

    @Column(name = "content_publication_date")
    private Date contentPublicationDate;

    @Column(name = "hidden")
    private boolean isHidden;

    @Column(name = "beta")
    private boolean isBeta;

    @Column(name = "internal")
    private boolean isInternal;

    @Column(name = "path")
    @URL
    private String path;

    @Column(name = "self_publishable")
    private boolean isSelfPublishable;

    @Column(name = "updated_content")
    private boolean updatedContent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "administrator_id")
    private AdminEntity administrator;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "creator_id")
    private AdminEntity creator;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "publisher_id")
    private AdminEntity publisher;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "content_publisher_id")
    private AdminEntity contentPublisher;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="offer_administrator", joinColumns = @JoinColumn(name="offer_internal_id"), inverseJoinColumns = @JoinColumn(name="administrator_internal_id"))
    private Set<AdminEntity> owners;

    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(value = FetchMode.SUBSELECT)
    @ElementCollection
    @CollectionTable(name="offer_tag", joinColumns = @JoinColumn(name = "offer_id"))
    private Set<String> tags = new HashSet<>();

    public EditableOfferEntity(String name, String description, Date creationDate, Date modificationDate, double revision, String commmunitySupportUrl, Date publicationDate, Date firstPublicationDate, Date contentPublicationDate, boolean isHidden, boolean isBeta, boolean isInternal, String path, boolean isSelfPublishable, boolean updatedContent, AdminEntity administrator, AdminEntity creator, AdminEntity publisher, AdminEntity contentPublisher, Set<AdminEntity> owners, Set<String> tags) {
        super(name, description, creationDate, modificationDate, revision);
        this.commmunitySupportUrl = commmunitySupportUrl;
        this.publicationDate = publicationDate;
        this.firstPublicationDate = firstPublicationDate;
        this.contentPublicationDate = contentPublicationDate;
        this.isHidden = isHidden;
        this.isBeta = isBeta;
        this.isInternal = isInternal;
        this.path = path;
        this.isSelfPublishable = isSelfPublishable;
        this.updatedContent = updatedContent;
        this.administrator = administrator;
        this.creator = creator;
        this.publisher = publisher;
        this.contentPublisher = contentPublisher;
        this.owners = owners;
        this.tags = tags;
    }
}
