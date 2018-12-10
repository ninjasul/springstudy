package com.ninjasul.spring.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    // 메시지 수신이 되면 알려줌.
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}