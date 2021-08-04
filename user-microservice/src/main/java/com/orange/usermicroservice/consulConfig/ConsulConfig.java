package com.orange.usermicroservice.consulConfig;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@RefreshScope
@Data
public class ConsulConfig {

    @Value("${adminMS.admin.creation}")
    private String creation;
}
