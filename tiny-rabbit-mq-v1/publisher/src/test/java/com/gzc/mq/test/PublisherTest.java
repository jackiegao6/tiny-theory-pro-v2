package com.gzc.mq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublisherTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage() throws IOException, TimeoutException {

        // 1. 建立连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.31.25");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();

        //2. 建立channel
        Channel channel = connection.createChannel();

        //3. 创建队列
        String queueName = "simple.test-queue";
        /*
            声明队列
                参数1：队列的名称 queueName
                参数2：队列是否支持持久化 false：不持久化处理
                参数3：队列是否排它：是否允许其它的connection下的channel连接
                参数4：是否空闲时自动删除，当最后一个consumer(消费者)断开之后，队列将自动删除。
                参数5：参数是rabbitmq的一个扩展，功能非常强大，基本是AMPQ中没有的。这里我们先传递null
         */
        channel.queueDeclare(queueName, false, false, false, null);

        // 4. 发送消息
        String message = "hello gzc!";
        /*
            发送消息：
                参数1：exchange 交换机 没有就设置为 "" 值就可以了
                参数2：routingKey 路由的key 现在没有设置key，直接使用队列的名字queueName
                参数3：发送数据到队列的时候，是否要带一些参数。直接赋值null即可
                参数4：body 向队列中发送的消息数据
         */
        channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("发送消息成功:" + message);

        channel.close();
        connection.close();
    }

    @Test
    public void testSendMessage_withTemplate(){
        // 队列名称
        String queueName = "simple.test-queue";
        // 消息
        String message = "hello, spring amqp!";
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, message);

    }

    @Test
    public void testSendMessage_withTemplate_tooMuch() throws InterruptedException {
        // 队列名称
        String queueName = "simple.test-queue";
        // 消息
        String message = "hello, message_";
        for (int i = 0; i < 50; i++) {
            // 发送消息
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testSendMessage_withExchange() throws InterruptedException {
        String exchangeName = "test.exchange.fanout";
        String message = "hello, message_";
        for (int i = 0; i < 1; i++) {
            // 发送消息
            rabbitTemplate.convertAndSend(exchangeName, "",message + i);
            Thread.sleep(20);
        }
    }

}
