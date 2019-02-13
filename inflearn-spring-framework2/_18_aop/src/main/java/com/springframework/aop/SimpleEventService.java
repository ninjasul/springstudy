package com.springframework.aop;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SimpleEventService implements EventService {

    @Override
    @PerfLogging
    public void createEvent() {

        try {
            Thread.sleep(1000);
        } catch( Exception e ) {
            e.printStackTrace();
        }

        log.info("Created an event");
    }

    @Override
    @PerfLogging
    public void publishEvent() {

        try {
            Thread.sleep(2000);
        } catch( Exception e ) {
            e.printStackTrace();
        }

        log.info("Published an event");
    }

    @Override
    public void deleteEvent() {
        log.info("Deleted an event");
    }
}