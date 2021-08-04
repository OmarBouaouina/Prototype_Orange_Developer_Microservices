package com.orange.adminmicroservice.service.impl;

import com.orange.adminmicroservice.model.dto.CreateOfferDTO;
import com.orange.adminmicroservice.model.dto.OfferWithOwnersDTO;
import com.orange.adminmicroservice.model.dto.UpdateOfferDTO;
import com.orange.adminmicroservice.model.entity.AdminEntity;
import com.orange.adminmicroservice.model.entity.EditableOfferEntity;
import com.orange.adminmicroservice.repository.AdminRepository;
import com.orange.adminmicroservice.repository.OfferRepository;
import com.orange.adminmicroservice.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public OfferWithOwnersDTO createOffer(CreateOfferDTO offerToCreate) {
        Set<AdminEntity> owners = new HashSet<>();
        for(String ownerNaturalId : offerToCreate.getOwnersNaturalIds()){
            owners.add(adminRepository.findAdminByNaturalId(ownerNaturalId));
        }
        EditableOfferEntity offerToSave = new EditableOfferEntity(offerToCreate.getName(), offerToCreate.getDescription(), new Date(System.currentTimeMillis()),
                null, offerToCreate.getRevision(), offerToCreate.getCommmunitySupportUrl(), null, null, null, offerToCreate.isHidden(), offerToCreate.isBeta(),
                offerToCreate.isInternal(), offerToCreate.getPath()+"/v"+new BigDecimal(String.valueOf(offerToCreate.getRevision())).intValue(),
                offerToCreate.isSelfPublishable(), false, adminRepository.findAdminByNaturalId(offerToCreate.getAdminNaturalId()),
                adminRepository.findAdminByNaturalId(offerToCreate.getCreatorNaturalId()), null, null, owners, offerToCreate.getTags());
        offerRepository.save(offerToSave);
        OfferWithOwnersDTO createdOffer = new OfferWithOwnersDTO(offerToSave);
        return createdOffer;
    }

    @Override
    public Set<String> findAllTags(){
        Set<String> tags = new HashSet<>();
        for(EditableOfferEntity offer : offerRepository.findAll()){
            tags.addAll(offer.getTags());
        }
        return tags;
    }

    @Override
    public OfferWithOwnersDTO getEditableOfferById(String offerId) {
        EditableOfferEntity editableOffer = offerRepository.findOfferById(offerId);
        if(editableOffer != null){
            return new OfferWithOwnersDTO(editableOffer);
        } else {
            return null;
        }
    }

    @Override
    public List<OfferWithOwnersDTO> getAllEditableOffers() {
        List<OfferWithOwnersDTO> allOffers = new ArrayList<>();
        for(EditableOfferEntity editableOffer: offerRepository.findAll()){
            allOffers.add(new OfferWithOwnersDTO(editableOffer));
        }
        return allOffers;
    }

    @Override
    public OfferWithOwnersDTO deleteEditableOfferById(String offerId) {
        EditableOfferEntity offerToDelete = offerRepository.findOfferById(offerId);
        if(offerToDelete != null){
            OfferWithOwnersDTO deletedOffer = new OfferWithOwnersDTO(offerToDelete);
            offerRepository.delete(offerToDelete);
            return deletedOffer;
        } else {
            return null;
        }
    }

    @Override
    public OfferWithOwnersDTO updateOffer(String offerId, UpdateOfferDTO offerUpdate) {
        EditableOfferEntity offerToUpdate = offerRepository.findOfferById(offerId);
        if(offerToUpdate != null){
            if(offerUpdate.getName() != null && offerUpdate.getName().length() != 0) { offerToUpdate.setName(offerUpdate.getName());}
            if(offerUpdate.getDescription() != null && offerUpdate.getDescription().length() != 0) { offerToUpdate.setDescription(offerUpdate.getDescription());}
            offerToUpdate.setHidden(offerUpdate.isHidden());
            offerToUpdate.setBeta(offerUpdate.isBeta());
            offerToUpdate.setInternal(offerUpdate.isInternal());
            offerToUpdate.setSelfPublishable(offerUpdate.isSelfPublishable());
            if(offerUpdate.getOwners() != null) { offerToUpdate.setOwners(offerUpdate.getOwners());}
            if(offerUpdate.getTags() != null) { offerToUpdate.setTags(offerUpdate.getTags()); }
            offerRepository.save(offerToUpdate);
            return new OfferWithOwnersDTO(offerToUpdate);
        }
        return null;
    }
}
