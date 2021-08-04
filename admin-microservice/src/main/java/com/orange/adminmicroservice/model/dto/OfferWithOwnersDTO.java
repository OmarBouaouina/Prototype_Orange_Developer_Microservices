package com.orange.adminmicroservice.model.dto;

import com.orange.adminmicroservice.model.entity.AdminEntity;
import com.orange.adminmicroservice.model.entity.EditableOfferEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OfferWithOwnersDTO {

    private String id;

    private String name;

    private String description;

    private Set<String> owners = new HashSet<>();

    public OfferWithOwnersDTO(EditableOfferEntity offer){
        this.id = offer.getId();
        this.name = offer.getName();
        this.description = offer.getDescription();
        for(AdminEntity admin : offer.getOwners()){
            this.owners.add(admin.getNaturalId());
        }
    }
}
