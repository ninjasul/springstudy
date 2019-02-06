package com.ninjasul.springframework.applicationcontext;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BookServiceTest {

    @Test
    public void bean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        BookService bookService = (BookService)context.getBean("bookService");
        assertNotNull(bookService);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        assertTrue(Arrays.toString(beanDefinitionNames).contains("bookService"));
    }
}