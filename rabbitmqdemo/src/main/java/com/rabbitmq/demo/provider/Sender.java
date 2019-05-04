package com.rabbitmq.demo.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void send(){
        String context="hello rabbitmq"+new Date();
        System.out.println("发送消息为："+context);
        this.amqpTemplate.convertAndSend("rabbitmq_test",context);

    }
}

