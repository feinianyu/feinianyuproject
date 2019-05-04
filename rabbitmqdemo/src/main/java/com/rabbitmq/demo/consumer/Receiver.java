package com.rabbitmq.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="rabbitmq_test")
public class Receiver {
    @RabbitHandler
    public void process(String str){
        System.out.println("接收者："+str);
    }
}
