package com.orange.adminmicroservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class CreateOfferDTO {

    private String name;

    private String description;

    private double revision;

    private String commmunitySupportUrl;

    private boolean isHidden;

    private boolean isBeta;

    private boolean isInternal;

    private String path;

    private boolean isSelfPublishable;

    private String adminNaturalId;

    private String creatorNaturalId;

    private Set<String> ownersNaturalIds;

    private Set<String> tags;
}
