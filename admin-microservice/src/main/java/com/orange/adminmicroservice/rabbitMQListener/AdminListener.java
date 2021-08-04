package com.orange.adminmicroservice.rabbitMQListener;

import com.orange.adminmicroservice.model.dto.AdminCreatedDTO;
import com.orange.adminmicroservice.model.dto.AdminUpdatedDTO;
import com.orange.adminmicroservice.model.entity.AdminEntity;
import com.orange.adminmicroservice.rabbitMQConfig.AdminConfig;
import com.orange.adminmicroservice.repository.AdminRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminListener {

    @Autowired
    private AdminRepository adminRepository;

    //@RabbitListener(queues = "#{consulConfig.getCreation}")
    @RabbitListener(queues = AdminConfig.ADMIN_CREATION_ADMIN_MS)
    public void adminCreationListener(AdminCreatedDTO consumedAdmin){
        AdminEntity admin = new AdminEntity(consumedAdmin.getId(), consumedAdmin.getNaturalId(), consumedAdmin.getEmail(), consumedAdmin.getUserType(), consumedAdmin.getRoles(), consumedAdmin.getPermissions());
        adminRepository.save(admin);
        System.out.println("Admin added.");
    }

    @RabbitListener(queues = AdminConfig.ADMIN_UPDATE_ADMIN_MS)
    public void adminUpdateListener(AdminUpdatedDTO consumedAdmin){
        AdminEntity adminToUpdate = adminRepository.findAdminByNaturalId(consumedAdmin.getNaturalId());
        if(adminToUpdate != null){
            adminToUpdate.setEmail(consumedAdmin.getEmail());
            adminToUpdate.setRoles(consumedAdmin.getRoles());
            adminToUpdate.setPermissions(consumedAdmin.getPermissions());
            adminRepository.save(adminToUpdate);
        } else {
            System.out.println("Admin to update not found");
        }
    }

    @RabbitListener(queues = AdminConfig.ADMIN_DELETION_ADMIN_MS)
    public void adminDeletionListener(String naturalId){
        AdminEntity adminToDelete = adminRepository.findAdminByNaturalId(naturalId);
        if(adminToDelete != null){
            adminRepository.delete(adminToDelete);
            System.out.println("Admin deleted");
        } else {
            System.out.println("Admin to delete not found");
        }
    }
}
