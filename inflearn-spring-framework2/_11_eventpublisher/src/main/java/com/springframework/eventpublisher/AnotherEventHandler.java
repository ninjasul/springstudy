package com.springframework.eventpublisher;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class AnotherEventHandler {

    @EventListener
    //@Order(Ordered.HIGHEST_PRECEDENCE+1)
    @Async
    public void handle(MyEvent event) {
        log.info(Thread.currentThread().toString());
        log.info("another event handler: {}", event.getData());
    }

}