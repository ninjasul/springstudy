package com.ninjasul.tobyspring31.config;

import com.ninjasul.tobyspring31.factorybean.Message;
import com.ninjasul.tobyspring31.factorybean.MessageFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class AppConfig {

    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol("SMTP");
        javaMailSender.setHost("127.0.0.1");
        javaMailSender.setPort(25);
        return javaMailSender;
    }

    @Bean(name="message")
    public MessageFactoryBean messageFactoryBean() {
        return new MessageFactoryBean();
    }

    @Bean
    public Message message() throws Exception {
        return messageFactoryBean().getObject();
    }
}