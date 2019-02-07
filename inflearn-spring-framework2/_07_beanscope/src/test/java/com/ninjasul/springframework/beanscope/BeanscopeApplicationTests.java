package com.ninjasul.springframework.beanscope;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class BeanscopeApplicationTests {

    @Autowired
    ApplicationContext context;

    @Autowired
    Singleton singleton;

    @Autowired
    Prototype prototype;
    
    @Test
    public void contextLoads() {
    }

    @Test
    public void beans() {
        log.info("singleton: {}", singleton);
        log.info("singleton: {}", singleton);
        log.info("singleton: {}", singleton);

        log.info("prototype: {}", prototype);
        log.info("prototype: {}", prototype);
        log.info("prototype: {}", prototype);

        log.info("singleton.getPrototype(): {}", singleton.getPrototype());
        log.info("singleton.getPrototype(): {}", singleton.getPrototype());
        log.info("singleton.getPrototype(): {}", singleton.getPrototype());
    }
}

