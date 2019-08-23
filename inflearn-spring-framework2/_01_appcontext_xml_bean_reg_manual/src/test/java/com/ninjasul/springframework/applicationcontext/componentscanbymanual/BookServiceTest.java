package com.ninjasul.springframework.applicationcontext.componentscanbymanual;

import com.ninjasul.springframework.applicationcontext.componentscanbymanual.BookService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BookServiceTest {

    @Test
    public void bean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        BookService bookService = (BookService)context.getBean("bookService");
        assertNotNull(bookService);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        assertTrue(Arrays.toString(beanDefinitionNames).contains("bookService"));
    }
}