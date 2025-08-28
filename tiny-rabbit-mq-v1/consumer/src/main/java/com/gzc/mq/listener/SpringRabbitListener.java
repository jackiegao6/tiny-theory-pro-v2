package com.gzc.mq.listener;


import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {

    private static int cnt_1 = 0;
    private static int cnt_2 = 0;

    @RabbitListener(queues = "simple.test-queue")
    public void listenSimpleQueueMessage1(String msg) throws InterruptedException {
        System.out.println("spring consumer No.1 get message: " + msg + "cnt is" + (cnt_1++));
        Thread.sleep(50);
    }

    @RabbitListener(queues = "simple.test-queue")
    public void listenSimpleQueueMessage2(String msg) throws InterruptedException {
        System.out.println("spring consumer No.2 get message: " + msg + "cnt is" + (cnt_2++));
        Thread.sleep(100);
    }

    @RabbitListener(queues = "test.exchange.fanout.queue1")
    public void listenFanoutQueue1(String msg){
        System.out.println("消费者1 接收到fanout消息: " + msg);
    }

    @RabbitListener(queues = "test.exchange.fanout.queue2")
    public void listenFanoutQueue2(String msg){
        System.out.println("消费者2 接收到fanout消息: " + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "test.exchange.direct.queue1"),
            exchange = @Exchange(name = "test.exchange.direct", type = ExchangeTypes.DIRECT),
            key = {"k1", "k2"}
    ))
    public void listenDirectQueue1(String msg){
        System.out.println("消费者1 接收到direct消息: " + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "test.exchange.direct.queue2"),
            exchange = @Exchange(name = "test.exchange.direct", type = ExchangeTypes.DIRECT),
            key = {"k1","k3"}
    ))
    public void listenDirectQueue2(String msg){
        System.out.println("消费者2 接收到direct消息: " + msg);
    }


}
