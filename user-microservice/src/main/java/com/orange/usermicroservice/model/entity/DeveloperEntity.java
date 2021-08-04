package com.orange.usermicroservice.model.entity;

import com.orange.usermicroservice.custom.CuidGenerator;
import com.orange.usermicroservice.model.dto.DeveloperCreationDTO;
import com.orange.usermicroservice.model.enums.UserRole;
import com.orange.usermicroservice.model.enums.UserStatus;
import com.orange.usermicroservice.model.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "developer")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeveloperEntity extends UserEntity {

    @Column(name = "cuid", unique = true)
    private String cuid;

    @Column(name = "company_name")
    @NotBlank(message = "Company name is mandatory")
    private String companyName;

    @Column(name = "company_activity")
    @NotBlank(message = "Company activity is mandatory")
    private String companyActivity;

    @Column(name = "job")
    @NotBlank(message = "Job is mandatory")
    private String job;

    @Column(name = "country")
    @NotBlank(message = "Country name is mandatory")
    private String country;

    @Column(name = "newsletter")
    private boolean isNewsletterMember;

    public DeveloperEntity(String email, String password, Date creationDate, UserRole userRole, UserType userType, Set<String> userPermissions, String applicationName, String firstName, String lastName, String cuid, String companyName, String companyActivity, String job, String country, boolean isNewsletterMember) throws Exception {
        super(email, password, creationDate, userRole, UserStatus.PENDING, userType, null, applicationName, firstName, lastName);
        this.cuid = CuidGenerator.generateId();
        this.companyName = companyName;
        this.companyActivity = companyActivity;
        this.job = job;
        this.country = country;
        this.isNewsletterMember = isNewsletterMember;
    }

    public DeveloperEntity(DeveloperCreationDTO developerToCreate) throws Exception {
        super(developerToCreate.getEmail(), developerToCreate.getPassword(), new Date(System.currentTimeMillis()), UserRole.ROLE_DEVELOPER, UserStatus.PENDING, developerToCreate.getUserType(), null, developerToCreate.getApplicationName(), developerToCreate.getFirstName(), developerToCreate.getLastName());
        this.cuid = CuidGenerator.generateId();
        this.isNewsletterMember = developerToCreate.isNewsLetterMember();
        this.job = developerToCreate.getJob();
        this.country = developerToCreate.getCountry();
        this.companyName = developerToCreate.getCompanyName();
        this.companyActivity = developerToCreate.getCompanyActivity();
    }
}