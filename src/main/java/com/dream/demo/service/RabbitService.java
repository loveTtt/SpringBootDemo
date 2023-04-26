package com.dream.demo.service;

import com.dream.demo.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitService {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void monitorMq(String isReset){
        System.out.println(isReset);
    }
}
