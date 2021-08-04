package com.orange.usermicroservice.repository;

import com.orange.usermicroservice.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    OfferEntity findOfferById(String id);
}
