package com.orange.adminmicroservice.controller;

import com.orange.adminmicroservice.model.dto.CreateOfferDTO;
import com.orange.adminmicroservice.model.dto.OfferWithOwnersDTO;
import com.orange.adminmicroservice.model.dto.UpdateOfferDTO;
import com.orange.adminmicroservice.rabbitMQConfig.OfferConfig;
import com.orange.adminmicroservice.service.OfferService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("admins/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private RabbitTemplate template;

    /*
    Get all offers' tags
     */
    @GetMapping("/tags")
    public ResponseEntity<Set<String>> listOfferTags(){
        return new ResponseEntity<>(offerService.findAllTags(), HttpStatus.OK);
    }

    /*
    Get an offer by its id
     */
    @GetMapping("/{offerId}")
    public ResponseEntity<OfferWithOwnersDTO> getEditableOfferById(@PathVariable("offerId") String offerId){
        return new ResponseEntity<>(offerService.getEditableOfferById(offerId), HttpStatus.OK);
    }

    /*
    Get all offers
     */
    @GetMapping
    public ResponseEntity<List<OfferWithOwnersDTO>> getAllEditableOffers(){
        return new ResponseEntity<>(offerService.getAllEditableOffers(), HttpStatus.OK);
    }

    /*
    Create an offer
     */
    @PostMapping
    public ResponseEntity<OfferWithOwnersDTO> createOffer(@RequestBody CreateOfferDTO offer){
        OfferWithOwnersDTO createdOffer = offerService.createOffer(offer);
        if(createdOffer != null){
            template.convertAndSend(OfferConfig.OFFER_CREATION_EXCHANGE, "", createdOffer);
            return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
        }
        return null;
    }

    /*
    Update an offer
     */
    @PatchMapping("/{offerId}")
    public ResponseEntity<OfferWithOwnersDTO> updateOffer(@PathVariable("offerId") String offerId, @RequestBody UpdateOfferDTO offerUpdate){
        OfferWithOwnersDTO updatedOffer = offerService.updateOffer(offerId, offerUpdate);
        if(updatedOffer != null){
            template.convertAndSend(OfferConfig.OFFER_UPDATE_EXCHANGE, "", updatedOffer);
            return new ResponseEntity<>(updatedOffer, HttpStatus.ACCEPTED);
        }
        return null;
    }

    /*
    Delete an offer by its id
     */
    @DeleteMapping("/{offerId}")
    public ResponseEntity<OfferWithOwnersDTO> deleteOfferById(@PathVariable("offerId") String offerId){
        OfferWithOwnersDTO deletedOffer = offerService.deleteEditableOfferById(offerId);
        if(deletedOffer != null){
            template.convertAndSend(OfferConfig.OFFER_DELETION_EXCHANGE, "", offerId);
            return new ResponseEntity<>(deletedOffer, HttpStatus.OK);
        }
        return null;
    }
}
