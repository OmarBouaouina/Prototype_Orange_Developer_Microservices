package com.orange.usermicroservice.controller;

import com.orange.usermicroservice.model.dto.*;
import com.orange.usermicroservice.model.enums.AdminRole;
import com.orange.usermicroservice.rabbitMQConfig.AdminConfig;
import com.orange.usermicroservice.service.AdminService;
import com.orange.usermicroservice.service.DeveloperService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("users/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private RabbitTemplate template;

    /*
    Return the list of administrators
     */
    @GetMapping
    public ResponseEntity<List<AdminListDTO>> getAllAdmins(){
        return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
    }

    /*
    Return an Admin by its naturalId
     */
    @GetMapping("/{naturalId}")
    public ResponseEntity<AdminPostOperationDTO> getAdminById(@PathVariable("naturalId") String naturalId) {
        return new ResponseEntity<>(adminService.getAdminById(naturalId), HttpStatus.OK);
    }

    /*
    Return the list of roles
     */
    @GetMapping("/roles")
    public ResponseEntity<Set<AdminRole>> getAllAdminRoles(){
        return new ResponseEntity<>(adminService.getAllAdminRoles(), HttpStatus.OK);
    }

    /*
    Return the list of permissions
     */
    @GetMapping("/permissions")
    public ResponseEntity<Set<String>> getAllAdminPermissions(){
        return new ResponseEntity<>(adminService.getAllAdminPermissions(), HttpStatus.OK);
    }

    /*
    Create an Admin
     */
    @PostMapping
    public ResponseEntity<AdminPostOperationDTO> createAdmin(@RequestBody AdminCreationDTO admin) throws Exception {
        AdminPostOperationDTO createdAdmin = adminService.createAdmin(admin);
        if(createdAdmin != null) {
            template.convertAndSend(AdminConfig.ADMIN_CREATION_EXCHANGE, "", new AdminProducedDTO(createdAdmin));
            return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
        }
        return null;
    }

    /*
    Update an admin
     */
    @PatchMapping("/{naturalId}")
    public ResponseEntity<AdminPostOperationDTO> updateAdmin(@PathVariable("naturalId") String naturalId, @RequestBody AdminUpdateDTO adminUpdateDTO){
        AdminPostOperationDTO updatedAdmin = adminService.updateAdmin(naturalId, adminUpdateDTO);
        if(updatedAdmin != null){
            template.convertAndSend(AdminConfig.ADMIN_UPDATE_EXCHANGE, "", new AdminProducedDTO(updatedAdmin));
            return new ResponseEntity<>(updatedAdmin, HttpStatus.ACCEPTED);
        }
        return null;
    }

    /*
    Delete an Admin by its naturalId
     */
    @DeleteMapping("/{naturalId}")
    public ResponseEntity<AdminPostOperationDTO> deleteAdmin(@PathVariable("naturalId") String naturalId){
        AdminPostOperationDTO deletedAdmin = adminService.deleteAdmin(naturalId);
        if(deletedAdmin != null){
            template.convertAndSend(AdminConfig.ADMIN_DELETION_EXCHANGE, "", naturalId);
            return new ResponseEntity<>(deletedAdmin, HttpStatus.OK);
        }
        return null;
    }

//    @PatchMapping("/me/selfpublisher")
//    public ResponseEntity<AdminPostOperationDTO> addSelfPublisherRole(){
//        return new ResponseEntity<>(adminService.addSelfPublisherRole(), HttpStatus.ACCEPTED);
//    }

//    @GetMapping("/me")
//    public ResponseEntity<AdminPostOperationDTO> getConnectedAdmin(){
//        return new ResponseEntity<>(adminService.getConnectedAdmin(), HttpStatus.OK);
//    }
//
//    @GetMapping("/me/roles")
//    public ResponseEntity<Set<AdminRole>> getManagedRoles(){
//        return new ResponseEntity<>(adminService.getManagedRoles(), HttpStatus.OK);
//    }
}