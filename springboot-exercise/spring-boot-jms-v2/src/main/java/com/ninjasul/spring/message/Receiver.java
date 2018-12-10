package com.ninjasul.spring.message;

import com.ninjasul.spring.SpringBootJmsV2Application;
import com.ninjasul.spring.domain.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/***********************************************************************************************************************
    JmsTemplate은 기본적으로 sync 방식으로만 동작하는 receive 메소드를 이용하여 메시지를 수신할 수 있음.
    async 방식으로 메시지를 수신하려면 DefaultMessageListenerContainer 와 같은 listener container와
    캐시 기반 connection factory를 사용하는 것이 좋음.
 **********************************************************************************************************************/
@Component
public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(SpringBootJmsV2Application.class);

    @JmsListener(destination = "mailbox", containerFactory="myFactory")
    @SendTo("anothermailbox")
    public String receiveMessage(Email email) {
        logger.info("Received <" + email + ">");
        return email + " successfully received.";
    }
}