package com.orange.adminmicroservice.model.dto;

import com.orange.adminmicroservice.model.entity.AdminEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class UpdateOfferDTO {

    private String name;

    private String description;

    private boolean isHidden;

    private boolean isBeta;

    private boolean isInternal;

    private boolean isSelfPublishable;

    Set<AdminEntity> owners;

    Set<String> tags;
}
