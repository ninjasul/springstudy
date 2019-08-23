package com.ninjasul.springframework.applicationcontext.javaconfigbycomponentscan;

import com.ninjasul.springframework.applicationcontext.javaconfigbycomponentscan.AppConfig;
import com.ninjasul.springframework.applicationcontext.javaconfigbycomponentscan.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Log4j2
public class BookRepositoryTest {

    @Test
    public void bean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookRepository bookRepository = (BookRepository)context.getBean("bookRepository");
        assertNotNull(bookRepository);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        assertTrue(Arrays.toString(beanDefinitionNames).contains("bookRepository"));
    }
}