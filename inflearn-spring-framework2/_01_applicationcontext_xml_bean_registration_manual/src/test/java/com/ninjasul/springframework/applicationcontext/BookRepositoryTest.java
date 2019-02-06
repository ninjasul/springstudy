package com.ninjasul.springframework.applicationcontext;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BookRepositoryTest {

    @Test
    public void bean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        BookRepository bookRepository = (BookRepository)context.getBean("bookRepository");
        assertNotNull(bookRepository);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        assertTrue(Arrays.toString(beanDefinitionNames).contains("bookRepository"));
    }
}