package com.ninjasul.spring.config;

import com.ninjasul.spring.message.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

@Configuration
public class MessagingConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    // application.properties 에서 myqueue 값을 읽어옴.
    @Value("${myqueue}")
    private String queue;

    // DefaultMessageListenerContainer 인스턴스 정의
    @Bean
    public DefaultMessageListenerContainer messageListener() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();

        // 3개의 필수값 설정
        container.setConnectionFactory(this.connectionFactory);
        container.setDestinationName(queue);
        container.setMessageListener(new Consumer());

        return container;
    }
}