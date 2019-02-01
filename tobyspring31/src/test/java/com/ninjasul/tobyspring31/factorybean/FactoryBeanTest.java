package com.ninjasul.tobyspring31.factorybean;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FactoryBeanTest {

    @Autowired
    @Qualifier("message")
    private Message message;

    @Resource
    private MessageFactoryBean messageFactoryBean;

    @Autowired
    private ApplicationContext context;

    @Test
    public void getMessage() {
        assertEquals(message.getClass(), Message.class);
        assertEquals(messageFactoryBean.getClass(), MessageFactoryBean.class);

        assertEquals( "Factory Bean", message.getText() );
        assertEquals( "Factory Bean", messageFactoryBean.getText() );
    }


    @Test
    public void getMessageFromApplicationContext() {
        Object messageFromContext = context.getBean("message");
        Object messageFactoryBeanFromContext = context.getBean("&message");

        assertEquals(messageFromContext.getClass(), Message.class);
        assertEquals(messageFactoryBeanFromContext.getClass(), MessageFactoryBean.class);

        assertEquals( "Factory Bean", ((Message)messageFromContext).getText() );
        assertEquals( "Factory Bean", ((MessageFactoryBean)messageFactoryBeanFromContext).getText() );
    }
}