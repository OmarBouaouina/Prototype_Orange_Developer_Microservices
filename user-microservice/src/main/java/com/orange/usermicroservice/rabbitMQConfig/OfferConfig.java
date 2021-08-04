package com.orange.usermicroservice.rabbitMQConfig;

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
}
