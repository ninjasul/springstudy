package com.ninjasul.tobyspring31.config;

import com.ninjasul.tobyspring31.factorybean.MessageFactoryBean;
import com.ninjasul.tobyspring31.learningtest.jdk.jaxb.Sqlmap;
import com.ninjasul.tobyspring31.user.dao.UserDao;
import com.ninjasul.tobyspring31.user.service.DummyMailSender;
import com.ninjasul.tobyspring31.user.service.UserService;
import com.ninjasul.tobyspring31.user.service.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.xml.bind.Unmarshaller;

@Configuration
@Component
@EnableTransactionManagement
@Log4j2
public class AppConfig {

    @Autowired
    ApplicationContext applicationContext;

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
    public UserService userService() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao((UserDao)applicationContext.getBean("userDao"));
        userService.setMailSender(new DummyMailSender());
        return userService;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public Jaxb2Marshaller unmarshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath(Sqlmap.class.getPackage().getName());
        return jaxb2Marshaller;
    }

}