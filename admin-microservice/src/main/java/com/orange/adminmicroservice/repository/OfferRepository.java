package com.orange.adminmicroservice.repository;

import com.orange.adminmicroservice.model.entity.EditableOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<EditableOfferEntity, Long> {
    EditableOfferEntity findOfferById(String id);
}
