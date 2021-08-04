package com.orange.usermicroservice.controller;

import com.orange.usermicroservice.model.dto.*;
import com.orange.usermicroservice.rabbitMQConfig.AdminConfig;
import com.orange.usermicroservice.rabbitMQConfig.DeveloperConfig;
import com.orange.usermicroservice.service.DeveloperService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/developers")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private RabbitTemplate template;

    /*
    Return a Developer by its cuid
     */
    @GetMapping("/{cuid}")
    public ResponseEntity<DeveloperPostOperationDTO> getDeveloperByCuid(@PathVariable("cuid") String cuid){
        return new ResponseEntity<>(developerService.getDeveloperByCuid(cuid), HttpStatus.OK);
    }

    /*
    Return the list of Developers
     */
    @GetMapping
    public ResponseEntity<List<DeveloperListDTO>> getAllDevelopers(){
        return new ResponseEntity<>(developerService.getAllDevelopers(), HttpStatus.OK);
    }

    /*
    Create a Developer
     */
    @PostMapping
    public ResponseEntity<DeveloperPostOperationDTO> createDeveloper(@RequestBody DeveloperCreationDTO developer) throws Exception {
        DeveloperPostOperationDTO createdDeveloper = developerService.createDeveloper(developer);
        if(createdDeveloper != null){
            template.convertAndSend(DeveloperConfig.DEVELOPER_CREATION_EXCHANGE, "", new DeveloperProducedDTO(createdDeveloper));
            return new ResponseEntity<>(createdDeveloper, HttpStatus.CREATED);
        }
        return null;
    }

    /*
    Update a Developer
     */

    @PatchMapping("/{cuid}")
    public ResponseEntity<DeveloperPostOperationDTO> updateDeveloper(@PathVariable("cuid") String cuid, @RequestBody DeveloperUpdateDTO developerUpdateDTO){
        DeveloperPostOperationDTO updatedDeveloper = developerService.updateDeveloper(cuid, developerUpdateDTO);
        if(updatedDeveloper != null){
            template.convertAndSend(DeveloperConfig.DEVELOPER_UPDATE_EXCHANGE, "", new DeveloperProducedDTO(updatedDeveloper));
            return new ResponseEntity<>(updatedDeveloper, HttpStatus.ACCEPTED);
        }
        return null;
    }

    /*
    Delete a Developer
     */
    @DeleteMapping("/{cuid}")
    public ResponseEntity<DeveloperPostOperationDTO> deleteDeveloper(@PathVariable("cuid") String cuid) {
        DeveloperPostOperationDTO deletedDeveloper = developerService.deleteDeveloper(cuid);
        if(deletedDeveloper != null){
            template.convertAndSend(DeveloperConfig.DEVELOPER_DELETION_EXCHANGE, "", cuid);
            return new ResponseEntity<>(deletedDeveloper, HttpStatus.OK);
        }
        return null;
    }

    /*
    Delete all Developers
     */

    @DeleteMapping
    public ResponseEntity<String> deleteAllDevelopers(){
        developerService.deleteAllDevelopers();
        template.convertAndSend(DeveloperConfig.DEVELOPER_DELETION_EXCHANGE, "", true);
        return new ResponseEntity<>("All Developers are deleted.", HttpStatus.OK);
    }
}