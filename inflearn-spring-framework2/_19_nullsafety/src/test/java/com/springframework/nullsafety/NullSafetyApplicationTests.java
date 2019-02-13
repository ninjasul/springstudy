package com.springframework.nullsafety;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NullSafetyApplicationTests {

    @Autowired
    EventService eventService;

    @Test(expected = IllegalArgumentException.class)
    public void nonnull_parameter() {
        eventService.createEvent(null);
    }

    @Test(expected = IllegalStateException.class)
    public void nonull_return() {
        assertNull(eventService.getEvent());
    }
}

