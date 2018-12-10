package me.ninjasul.applicationcontext.event.sync.oldstyle;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SyncOldEventHandler implements ApplicationListener<SyncOldEvent> {

    @Override
    public void onApplicationEvent(SyncOldEvent event) {
        System.out.printf("[%s][%s]: Received an event. The data is %s\n", Thread.currentThread().toString(), this.getClass().getSimpleName(), event.getData());
    }
}