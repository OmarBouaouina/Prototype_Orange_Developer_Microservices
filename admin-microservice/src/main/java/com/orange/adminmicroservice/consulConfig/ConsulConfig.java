package com.orange.adminmicroservice.consulConfig;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Data
public class ConsulConfig {

    @Value("${adminMS.admin.creation}")
    private String creation;
}
