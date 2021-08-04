//package com.orange.adminmicroservice.model;
//
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "terms")
//@NoArgsConstructor
//@ToString
//public class TermsEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "internal_id")
//    private Long internalId;
//
//    @Column(name = "id", unique = true)
//    private String id;
//
//    @Column(name = "creation_date")
//    private Date creationDate;
//
//    @Column(name = "publication_date")
//    private Date publicationDate;
//
//    @Column(name = "url")
//    private String url;
//
//    @Column(name = "type")
//    private String type;
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
//    @JoinColumn(name = "product_id")
//    private ProductEntity product;
//}
