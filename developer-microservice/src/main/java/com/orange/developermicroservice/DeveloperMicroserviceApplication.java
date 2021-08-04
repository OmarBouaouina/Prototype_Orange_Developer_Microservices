package com.orange.developermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DeveloperMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeveloperMicroserviceApplication.class, args);
	}

}
