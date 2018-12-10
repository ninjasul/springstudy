package me.ninjasul.applicationcontext.event.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AsyncNewEventRunner implements ApplicationRunner {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventPublisher.publishEvent(new AsyncNewEvent(this, 10000));
    }
}