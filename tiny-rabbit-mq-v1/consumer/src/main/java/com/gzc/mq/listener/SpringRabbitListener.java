package com.gzc.mq.listener;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {


    @RabbitListener(queues = "simple.test-queue")
    public void listenSimpleQueueMessage(String msg){
        System.out.println("spring consumer get message: " + msg);
    }


}
