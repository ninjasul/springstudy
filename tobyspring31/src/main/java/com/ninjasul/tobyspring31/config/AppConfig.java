package com.ninjasul.tobyspring31.config;

import com.ninjasul.tobyspring31.factorybean.Message;
import com.ninjasul.tobyspring31.factorybean.MessageFactoryBean;
import com.ninjasul.tobyspring31.user.service.TxProxyFactoryBean;
import com.ninjasul.tobyspring31.user.service.UserService;
import com.ninjasul.tobyspring31.user.service.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
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
        log.info("messageFactoryBean()");
        return new MessageFactoryBean();
    }

   /* @Bean(name="userService")
    public TxProxyFactoryBean userService() {
        log.info("userService()");
        TxProxyFactoryBean txProxyFactoryBean = new TxProxyFactoryBean();
        txProxyFactoryBean.setTarget(applicationContext.getBean("userServiceImpl"));
        txProxyFactoryBean.setPattern("upgradeLevels");
        txProxyFactoryBean.setServiceInterface(UserService.class);
        txProxyFactoryBean.setTransactionManager((PlatformTransactionManager)applicationContext.getBean("transactionManager"));
        return txProxyFactoryBean;
    }*/
}