package com.ninjasul.tobyspring31.config;

import com.ninjasul.tobyspring31.factorybean.Message;
import com.ninjasul.tobyspring31.factorybean.MessageFactoryBean;
import com.ninjasul.tobyspring31.learningtest.jdk.proxy.NameMatchClassMethodPointcut;
import com.ninjasul.tobyspring31.user.dao.UserDao;
import com.ninjasul.tobyspring31.user.dao.UserDaoJdbc;
import com.ninjasul.tobyspring31.user.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Component
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
    public Pointcut transactionPointcut() {
        NameMatchClassMethodPointcut pointcut = new NameMatchClassMethodPointcut();
        pointcut.setMappedClassName("*ServiceImpl");
        pointcut.setMappedName("upgrade*");
        return pointcut;
    }

    @Bean
    public PointcutAdvisor transactionAdvisor() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice((TransactionAdvice)applicationContext.getBean("transactionAdvice"));
        advisor.setPointcut(transactionPointcut());
        return advisor;
    }


}