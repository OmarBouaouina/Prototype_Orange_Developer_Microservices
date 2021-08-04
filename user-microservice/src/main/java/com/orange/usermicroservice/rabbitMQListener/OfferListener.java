package com.orange.usermicroservice.rabbitMQListener;

import com.orange.usermicroservice.model.dto.OfferConsumedDTO;
import com.orange.usermicroservice.model.entity.AdminEntity;
import com.orange.usermicroservice.model.entity.OfferEntity;
import com.orange.usermicroservice.rabbitMQConfig.OfferConfig;
import com.orange.usermicroservice.repository.AdminRepository;
import com.orange.usermicroservice.repository.OfferRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class OfferListener {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @RabbitListener(queues = OfferConfig.OFFER_CREATION_USER_MS)
    public void offerCreationListener(OfferConsumedDTO consumedOffer){
        Set<AdminEntity> owners = new HashSet<>();
        for(String adminId : consumedOffer.getOwners()){
            owners.add(adminRepository.findAdminByNaturalId(adminId));
        }
        OfferEntity createdOffer = new OfferEntity(consumedOffer.getId(), consumedOffer.getName(), owners);
        offerRepository.save(createdOffer);
        System.out.println("Offer added.");
    }

    @RabbitListener(queues = OfferConfig.OFFER_UPDATE_USER_MS)
    public void offerUpdateListener(OfferConsumedDTO consumedOffer){
        Set<AdminEntity> owners = new HashSet<>();
        for(String adminId : consumedOffer.getOwners()){
            owners.add(adminRepository.findAdminByNaturalId(adminId));
        }
        OfferEntity updatedOffer = offerRepository.findOfferById(consumedOffer.getId());
        updatedOffer.setOwners(owners);
        offerRepository.save(updatedOffer);
        System.out.println("Offer updated.");
    }

    @RabbitListener(queues = OfferConfig.OFFER_DELETION_USER_MS)
    public void offerDeletionListener(String offerId){
        OfferEntity offerToDelete = offerRepository.findOfferById(offerId);
        if(offerToDelete != null){
            offerRepository.delete(offerToDelete);
            System.out.println("Offer deleted.");
        } else {
            System.out.println("Offer to delete not found.");
        }
    }
}
