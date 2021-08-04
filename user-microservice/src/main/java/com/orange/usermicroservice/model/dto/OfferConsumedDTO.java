package com.orange.usermicroservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OfferConsumedDTO {

    private String id;

    private String name;

    private String description;

    private Set<String> owners = new HashSet<>();
}
