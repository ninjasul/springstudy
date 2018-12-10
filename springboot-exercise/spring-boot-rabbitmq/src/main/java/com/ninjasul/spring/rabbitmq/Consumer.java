package com.ninjasul.spring.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues="${myqueue}")
    public void handler(String message) {
        logger.info("Consumer > " + message);
    }
}