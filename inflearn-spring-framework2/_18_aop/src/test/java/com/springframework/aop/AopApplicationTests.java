package com.springframework.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    EventService eventService;

    @Test
    public void aop() {
        eventService.createEvent();
        eventService.publishEvent();
        eventService.deleteEvent();
    }
}

