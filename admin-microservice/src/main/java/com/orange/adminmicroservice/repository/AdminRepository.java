package com.orange.adminmicroservice.repository;

import com.orange.adminmicroservice.model.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findAdminByNaturalId(String naturalId);
}
