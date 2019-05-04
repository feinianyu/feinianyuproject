package com.rabbitmq.demo.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicRev1 {
    private static final  String QUEUE_NAME="test_queue_topic1";
    /*
    使用这个对列名则无效，所有消费者都可以消费，queueBind不生效
    private static final  String QUEUE_NAME="test_queue_topic_1";
    */
    private static final  String EXCHANGE_NAME="test_exchange_topic";
    public static void main(String[] args) throws IOException, TimeoutException { ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);//一次只发一个
        String routingKey = "goods.delete";
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,routingKey);
        //定义消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            super.handleDelivery(consumerTag, envelope, properties, body);
            String msg = new String(body,"utf-8");
            System.out.println("[1]--topic-recv:" + msg);
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
