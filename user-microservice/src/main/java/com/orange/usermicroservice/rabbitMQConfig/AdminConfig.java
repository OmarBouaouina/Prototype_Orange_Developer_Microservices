package com.orange.usermicroservice.rabbitMQConfig;

import com.orange.usermicroservice.consulConfig.ConsulConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

public class AdminConfig {

    @Autowired
    private ConsulConfig consulConfig;

    public static final String ADMIN_CREATION_ADMIN_MS = "admin_creation_adminMS";
    public static final String ADMIN_DELETION_ADMIN_MS = "admin_deletion_adminMS";
    public static final String ADMIN_UPDATE_ADMIN_MS = "admin_update_adminMS";
    public static final String ADMIN_UPDATE_EXCHANGE = "admin_update_exchange";
    public static final String ADMIN_CREATION_EXCHANGE = "admin_creation_exchange";
    public static final String ADMIN_DELETION_EXCHANGE = "admin_deletion_exchange";

    @Bean
    public Queue adminCreationAdminMSQueue(){
        return new Queue(ADMIN_CREATION_ADMIN_MS);
    }

    @Bean
    public Queue adminUpdateAdminMSQueue() { return  new Queue(ADMIN_UPDATE_ADMIN_MS); }

    @Bean
    public Queue adminDeletionAdminMSQueue(){
        return new Queue(ADMIN_DELETION_ADMIN_MS);
    }

    @Bean
    public FanoutExchange adminCreationExchange(){
        return new FanoutExchange(ADMIN_CREATION_EXCHANGE);
    }

    @Bean
    public FanoutExchange adminUpdateExchange() { return new FanoutExchange(ADMIN_UPDATE_EXCHANGE);}

    @Bean
    public FanoutExchange adminDeletionExchange(){
        return new FanoutExchange(ADMIN_DELETION_EXCHANGE);
    }

    @Bean
    public Binding adminCreationAdminMSBinding(@Qualifier("adminDeletionAdminMSQueue") Queue adminDeletionAdminMSQueue, @Qualifier("adminDeletionExchange") FanoutExchange adminDeletionExchange){
        return BindingBuilder
                .bind(adminDeletionAdminMSQueue)
                .to(adminDeletionExchange);
    }

    @Bean
    public Binding adminUpdateAdminMSBinding(@Qualifier("adminUpdateAdminMSQueue") Queue adminUpdateAdminMSQueue, @Qualifier("adminUpdateExchange") FanoutExchange adminUpdateExchange){
        return BindingBuilder
                .bind(adminUpdateAdminMSQueue)
                .to(adminUpdateExchange);
    }

    @Bean
    public Binding adminDeletionAdminMSBinding(@Qualifier("adminCreationAdminMSQueue") Queue adminCreationAdminMSQueue, @Qualifier("adminCreationExchange") FanoutExchange adminCreationExchange){
        return BindingBuilder
                .bind(adminCreationAdminMSQueue)
                .to(adminCreationExchange);
    }
}
