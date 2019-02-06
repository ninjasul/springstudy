package com.ninjasul.springframework.applicationcontext;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.*;

@Log4j2
public class BookRepositoryTest {

    @Test
    public void bean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        BookRepository bookRepository = (BookRepository)context.getBean("bookRepository");
        assertNotNull(bookRepository);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        assertTrue(Arrays.toString(beanDefinitionNames).contains("bookRepository"));
    }
}