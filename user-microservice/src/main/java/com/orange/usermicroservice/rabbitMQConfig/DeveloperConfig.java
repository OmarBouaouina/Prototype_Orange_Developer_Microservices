package com.orange.usermicroservice.rabbitMQConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeveloperConfig {


    public static final String DEVELOPER_CREATION_DEVELOPER_MS = "developer_creation_developerMS";
    public static final String DEVELOPER_CREATION_EXCHANGE = "developer_creation_exchange";
    public static final String DEVELOPER_UPDATE_DEVELOPER_MS = "developer_update_developerMS";
    public static final String DEVELOPER_UPDATE_EXCHANGE = "developer_update_exchange";
    public static final String DEVELOPER_DELETION_DEVELOPER_MS = "developer_deletion_developerMS";
    public static final String DEVELOPER_DELETION_EXCHANGE = "developer_deletion_exchange";

    @Bean
    public Queue developerCreationDeveloperMSQueue(){
        return new Queue(DEVELOPER_CREATION_DEVELOPER_MS);
    }

    @Bean
    public Queue developerUpdateDeveloperMSQueue() { return new Queue(DEVELOPER_UPDATE_DEVELOPER_MS); }

    @Bean
    public Queue developerDeletionDeveloperMSQueue() { return new Queue(DEVELOPER_DELETION_DEVELOPER_MS); }

    @Bean
    public FanoutExchange developerCreationExchange(){
        return new FanoutExchange(DEVELOPER_CREATION_EXCHANGE);
    }

    @Bean
    public FanoutExchange developerUpdateExchange() { return new FanoutExchange(DEVELOPER_UPDATE_EXCHANGE); }

    @Bean
    public FanoutExchange developerDeletionExchange(){
        return new FanoutExchange(DEVELOPER_DELETION_EXCHANGE);
    }

    @Bean
    public Binding developerCreationDeveloperMSBinding(@Qualifier("developerCreationDeveloperMSQueue") Queue developerCreationDeveloperMSQueue, @Qualifier("developerCreationExchange") FanoutExchange developerCreationExchange){
        return BindingBuilder
                .bind(developerCreationDeveloperMSQueue)
                .to(developerCreationExchange);
    }

    @Bean
    public Binding developerUpdateDeveloperMSBinding(@Qualifier("developerUpdateDeveloperMSQueue") Queue developerUpdateDeveloperMSQueue, @Qualifier("developerUpdateExchange") FanoutExchange developerUpdateExchange){
        return BindingBuilder
                .bind(developerUpdateDeveloperMSQueue)
                .to(developerUpdateExchange);
    }

    @Bean
    public Binding developerDeletionDeveloperMSBinding(@Qualifier("developerDeletionDeveloperMSQueue") Queue developerDeletionDeveloperMSQueue, @Qualifier("developerDeletionExchange") FanoutExchange developerDeletionExchange){
        return BindingBuilder
                .bind(developerDeletionDeveloperMSQueue)
                .to(developerDeletionExchange);
    }
}
