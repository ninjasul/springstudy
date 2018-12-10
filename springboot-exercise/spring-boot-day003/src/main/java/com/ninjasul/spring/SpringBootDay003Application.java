package com.ninjasul.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDay003Application {


    private static final Logger logger = LoggerFactory.getLogger(SpringBootDay003Application.class);

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(SpringBootDay003Application.class, args);
        String hello = applicationContext.getBean("hello", String.class);
        String world = applicationContext.getBean("world", String.class);

        String hello1 = applicationContext.getBean("hello1", String.class);
        String world1 = applicationContext.getBean("world1", String.class);

        logger.info(hello);
        logger.info(world);
        logger.info(hello1);
        logger.info(world1);

    }
}
