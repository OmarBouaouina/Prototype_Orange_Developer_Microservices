package com.orange.developermicroservice.rabbitMQConfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeveloperConfig {

    public static final String DEVELOPER_CREATION_DEVELOPER_MS = "developer_creation_developerMS";
    public static final String DEVELOPER_UPDATE_DEVELOPER_MS = "developer_update_developerMS";
    public static final String DEVELOPER_DELETION_DEVELOPER_MS = "developer_deletion_developerMS";
}