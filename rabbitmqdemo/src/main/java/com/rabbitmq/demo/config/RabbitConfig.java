package com.rabbitmq.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitConfig {
    @Bean
    public Queue helloqueue(){
        return new Queue("rabbitmq_test") ;
    }
}
