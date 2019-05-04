package com.rabbitmq.demo.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutRev1 {
    private static final  String QUEUE_NAME="test_queue_fanout_email";
    private static final  String EXCHANGE_NAME="test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException { ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        channel.basicQos(1);
        //定义消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            super.handleDelivery(consumerTag, envelope, properties, body);
            String msg = new String(body,"utf-8");
            System.out.println("[1]---recv:" + msg);
            try { Thread.sleep(2);
            } catch (InterruptedException e) { e.printStackTrace();
            }finally { System.out.println("[1] done");
                channel.basicAck(envelope.getDeliveryTag(),false);
            } } };
        //监听队列
//        boolean autoACK = true;//自动应答
        boolean autoACK = false;
        channel.basicConsume(QUEUE_NAME,autoACK,defaultConsumer);
    }

}
