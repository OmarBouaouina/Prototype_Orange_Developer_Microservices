package com.orange.developermicroservice.rabbitMQListener;

import com.orange.developermicroservice.model.dto.DeveloperCreatedDTO;
import com.orange.developermicroservice.model.dto.DeveloperUpdatedDTO;
import com.orange.developermicroservice.model.entity.DeveloperEntity;
import com.orange.developermicroservice.rabbitMQConfig.DeveloperConfig;
import com.orange.developermicroservice.repository.DeveloperRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeveloperListener {

    @Autowired
    private DeveloperRepository developerRepository;

    @RabbitListener(queues = DeveloperConfig.DEVELOPER_CREATION_DEVELOPER_MS)
    public void developerCreationListener(DeveloperCreatedDTO consumedDeveloper){
        DeveloperEntity developer = new DeveloperEntity(consumedDeveloper.getId(), consumedDeveloper.getCuid(), consumedDeveloper.getEmail(), consumedDeveloper.getUserType(), consumedDeveloper.getPermissions());
        developerRepository.save(developer);
        System.out.println("Developer added.");
    }

    @RabbitListener(queues = DeveloperConfig.DEVELOPER_UPDATE_DEVELOPER_MS)
    public void developerUpdateListener(DeveloperUpdatedDTO consumedDeveloper){
        DeveloperEntity developerToUpdate = developerRepository.findDeveloperByCuid(consumedDeveloper.getCuid());
        if(developerToUpdate != null){
            developerToUpdate.setEmail(consumedDeveloper.getEmail());
            developerToUpdate.setPermissions(consumedDeveloper.getPermissions());
            developerRepository.save(developerToUpdate);
        } else {
            System.out.println("Developer to update not found");
        }
    }

    @RabbitListener(queues = DeveloperConfig.DEVELOPER_DELETION_DEVELOPER_MS)
    public void developerDeletionListener(String cuid){
        DeveloperEntity developerToDelete = developerRepository.findDeveloperByCuid(cuid);
        if(developerToDelete != null){
            developerRepository.delete(developerToDelete);
            System.out.println("Developer deleted");
        } else {
            System.out.println("Developer to delete not found");
        }
    }
}
