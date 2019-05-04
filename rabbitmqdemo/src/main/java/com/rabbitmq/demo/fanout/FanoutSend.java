package com.rabbitmq.demo.fanout;



import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutSend {
    private static final String EXCHANGE_NAME="test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        //交换机不能存储消息,发送完就没有了
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        String msg = "hello fanout";
        //发送消息
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
        System.out.println("send" + msg);
        channel.close();
        connection.close();
    }

}
