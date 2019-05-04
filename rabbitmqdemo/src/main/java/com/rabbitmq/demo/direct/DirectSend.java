package com.rabbitmq.demo.direct;



import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectSend {
    private static final String EXCHANGE_NAME="test_exchange_direct";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        //交换机不能存储消息,发送完就没有了
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        String msg = "hello drect";
        String routingKey = "success";
        //发送消息
        channel.basicPublish(EXCHANGE_NAME,routingKey,null,msg.getBytes());
        System.out.println("send" + msg);
        channel.close();
        connection.close();
    }

}
