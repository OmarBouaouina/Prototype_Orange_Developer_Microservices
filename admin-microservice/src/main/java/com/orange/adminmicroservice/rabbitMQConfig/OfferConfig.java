package com.orange.adminmicroservice.rabbitMQConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfferConfig {

    public static final String OFFER_CREATION_USER_MS = "offer_creation_userMS";
    public static final String OFFER_UPDATE_USER_MS = "offer_update_userMS";
    public static final String OFFER_DELETION_USER_MS = "offer_deletion_userMS";
    public static final String OFFER_CREATION_EXCHANGE = "offer_creation_exchange";
    public static final String OFFER_UPDATE_EXCHANGE = "offer_update_exchange";
    public static final String OFFER_DELETION_EXCHANGE = "offer_deletion_exchange";

    @Bean
    public Queue offerCreationUserMSQueue() { return new Queue(OFFER_CREATION_USER_MS); }

    @Bean
    public Queue offerUpdateUserMSQueue() { return new Queue(OFFER_UPDATE_USER_MS); }

    @Bean
    public Queue offerDeletionUserMSQueue() { return new Queue(OFFER_DELETION_USER_MS); }

    @Bean
    public FanoutExchange offerCreationExchange() { return new FanoutExchange(OFFER_CREATION_EXCHANGE); }

    @Bean
    public FanoutExchange offerUpdateExchange() { return new FanoutExchange(OFFER_UPDATE_EXCHANGE); }

    @Bean
    public FanoutExchange offerDeletionExchange() { return new FanoutExchange(OFFER_DELETION_EXCHANGE); }

    @Bean
    public Binding offerCreationUserMSBinding(@Qualifier("offerCreationUserMSQueue") Queue offerCreationUserMSQueue, @Qualifier("offerCreationExchange") FanoutExchange offerCreationExchange){
        return BindingBuilder
                .bind(offerCreationUserMSQueue)
                .to(offerCreationExchange);
    }

    @Bean
    public Binding offerUpdateUserMSBinding(@Qualifier("offerUpdateUserMSQueue") Queue offerUpdateUserMSQueue, @Qualifier("offerUpdateExchange") FanoutExchange offerUpdateExchange){
        return BindingBuilder
                .bind(offerUpdateUserMSQueue)
                .to(offerUpdateExchange);
    }

    @Bean
    public Binding offerDeletionUserMSBinding(@Qualifier("offerDeletionUserMSQueue") Queue offerDeletionUserMSQueue, @Qualifier("offerDeletionExchange") FanoutExchange offerDeletionExchange){
        return BindingBuilder
                .bind(offerDeletionUserMSQueue)
                .to(offerDeletionExchange);
    }
}
