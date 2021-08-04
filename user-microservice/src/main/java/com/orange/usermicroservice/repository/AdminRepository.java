package com.orange.usermicroservice.repository;

import com.orange.usermicroservice.model.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findAdminByNaturalId(String naturalId);
    AdminEntity findAdminByEmail(String email);
}
