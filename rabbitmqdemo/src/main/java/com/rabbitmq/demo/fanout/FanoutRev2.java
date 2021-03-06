package com.rabbitmq.demo.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutRev2 {
    private static final  String QUEUE_NAME="test_queue_fanout_sms";
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
            System.out.println("[2]---recv:" + msg);
            try { Thread.sleep(2);
            } catch (InterruptedException e) { e.printStackTrace();
            }finally { System.out.println("[2] done");
                channel.basicAck(envelope.getDeliveryTag(),false);
            } } };
        boolean autoACK = false;
        channel.basicConsume(QUEUE_NAME,autoACK,defaultConsumer);
    }

}
