package com.ninjasul.spring.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/***********************************************************************************************************************
 MessageListener: 비동기로 전달된 메시지를 받는 인터페이스
***********************************************************************************************************************/
public class Consumer implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Override
    public void onMessage( Message message ) {
        try {
            logger.info( "Consumer > " + message.getBody(Object.class));
        } catch ( JMSException ex ) {
            ex.printStackTrace();
        }
    }
}