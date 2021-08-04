package com.orange.adminmicroservice.service;

import com.orange.adminmicroservice.model.dto.CreateOfferDTO;
import com.orange.adminmicroservice.model.dto.OfferWithOwnersDTO;
import com.orange.adminmicroservice.model.dto.UpdateOfferDTO;

import java.util.List;
import java.util.Set;

public interface OfferService {

    OfferWithOwnersDTO createOffer(CreateOfferDTO createdOfferDTO);

    Set<String> findAllTags();

    OfferWithOwnersDTO getEditableOfferById(String offerId);

    List<OfferWithOwnersDTO> getAllEditableOffers();

    OfferWithOwnersDTO deleteEditableOfferById(String offerId);

    OfferWithOwnersDTO updateOffer(String offerId, UpdateOfferDTO offerUpdate);
}
