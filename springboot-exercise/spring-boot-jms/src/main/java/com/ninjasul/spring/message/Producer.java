package com.ninjasul.spring.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private JmsTemplate jmsTemplate;

    public Producer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    // 큐(목적지)로 메시지를 전송
    public void sendTo( String queue, String message ) {
        this.jmsTemplate.convertAndSend( queue, message );
        logger.info("Producer > 메시지 전송");
    }
}