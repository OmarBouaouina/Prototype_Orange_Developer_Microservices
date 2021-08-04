package com.orange.developermicroservice.repository;

import com.orange.developermicroservice.model.entity.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Long> {
    DeveloperEntity findDeveloperByCuid(String cuid);
}
