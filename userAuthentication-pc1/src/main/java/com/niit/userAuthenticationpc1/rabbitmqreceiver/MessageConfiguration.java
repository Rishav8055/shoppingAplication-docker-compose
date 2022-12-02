package com.niit.userAuthenticationpc1.rabbitmqreceiver;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

    @Bean
    public Jackson2JsonMessageConverter getProducerJacksonConverter(){
            return new Jackson2JsonMessageConverter();
    }
}
