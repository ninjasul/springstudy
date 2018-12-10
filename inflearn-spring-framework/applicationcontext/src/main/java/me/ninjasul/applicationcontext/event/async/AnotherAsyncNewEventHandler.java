package me.ninjasul.applicationcontext.event.async;

import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AnotherAsyncNewEventHandler {

    @EventListener
    @Async
    public void handle(AsyncNewEvent event) {
        System.out.printf("[%s][%s]: Received an event. The data is %s\n", Thread.currentThread().toString(), this.getClass().getSimpleName(), event.getData());
    }
}