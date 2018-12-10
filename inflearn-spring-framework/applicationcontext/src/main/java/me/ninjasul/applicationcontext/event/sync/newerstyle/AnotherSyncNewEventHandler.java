package me.ninjasul.applicationcontext.event.sync.newerstyle;

import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class AnotherSyncNewEventHandler {

    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public void handle(SyncNewEvent event) {
        System.out.printf("[%s][%s]: Received an event. The data is %s\n", Thread.currentThread().toString(), this.getClass().getSimpleName(), event.getData());
    }
}