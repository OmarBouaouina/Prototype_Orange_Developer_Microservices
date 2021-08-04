package com.orange.adminmicroservice.model.entity;

import com.orange.adminmicroservice.custom.IdGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Table(name = "editable")
@NoArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;

    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "modification_date")
    private Date modificationDate;

    @Column(name = "revision")
    private double revision;

//    @Column(name = "publication_type")
//    private String publicationType;

    public EditableEntity(String name, String description, Date creationDate, Date modificationDate, double revision) {
        this.id = IdGenerator.generateId();
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.revision = Double.parseDouble(new DecimalFormat("#.0").format(revision));
//        this.publicationType = publicationType;
    }
}