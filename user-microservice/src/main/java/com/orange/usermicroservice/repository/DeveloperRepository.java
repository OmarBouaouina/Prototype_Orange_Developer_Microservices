package com.orange.usermicroservice.repository;

import com.orange.usermicroservice.model.entity.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Long> {
    DeveloperEntity findDeveloperByCuid(String cuid);
}
