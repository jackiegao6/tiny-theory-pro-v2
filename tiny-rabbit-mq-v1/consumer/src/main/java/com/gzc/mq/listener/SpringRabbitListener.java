package com.gzc.mq.listener;


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


}
