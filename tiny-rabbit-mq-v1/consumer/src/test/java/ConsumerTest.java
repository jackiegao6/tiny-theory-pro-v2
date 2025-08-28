import com.rabbitmq.client.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerTest {

    @Test
    public void testReceiveMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.31.25");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        String queueName = "simple.test-queue";
        channel.queueDeclare(queueName, false, false, false, null);

        // 4.订阅消息
		/*
            参数1：消费者消费的队列名称
            参数2：收到消息后自动应答，通知rabbitmq自动剔除已经被消费的消息
            参数3：接口消息的回调：一旦队列下有新的消息，则自动回调DefaultConsumer对象下的handleDelivery方法
            把消息以入参传入到该方法中
         */
        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("接收到消息: " + message);
            }
        });
        System.out.println("等待接收消息");

    }


}
