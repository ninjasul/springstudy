package me.ninjasul.applicationcontext.event.sync.oldstyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SyncOldEventRunner implements ApplicationRunner {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventPublisher.publishEvent(new SyncOldEvent( this, 100));
    }

}