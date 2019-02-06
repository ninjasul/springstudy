package com.ninjasul.springframework.applicationcontext;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BookServiceTest {

    @Test
    public void bean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookService bookService = (BookService)context.getBean("bookService");
        assertNotNull(bookService);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        assertTrue(Arrays.toString(beanDefinitionNames).contains("bookService"));
    }
}