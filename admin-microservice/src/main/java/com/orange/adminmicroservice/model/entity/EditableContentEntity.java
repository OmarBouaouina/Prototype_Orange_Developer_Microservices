//package com.orange.adminmicroservice.model;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "editable_content")
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//public class EditableContentEntity{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "internal_id")
//    private Long internalId;
//
//    @Column(name = "id", unique = true)
//    private String id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "type")
//    private String type;
//
//    public EditableContentEntity(String id, String name, String type) {
//        this.id = id;
//        this.name = name;
//        this.type = type;
//    }
//}
