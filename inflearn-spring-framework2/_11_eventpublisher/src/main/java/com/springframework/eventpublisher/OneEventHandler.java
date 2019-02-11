package com.springframework.eventpublisher;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OneEventHandler {

    @EventListener
    //@Order(Ordered.HIGHEST_PRECEDENCE)
    @Async
    public void handle(MyEvent event) {
        log.info(Thread.currentThread().toString());
        log.info("one event handler: {}", event.getData());
    }

    // ApplicationContext predefined event handler
    @EventListener
    @Async
    public void handle(ContextRefreshedEvent event) {
        log.info(Thread.currentThread().toString());
        log.info("ContextRefreshedEvent");
    }

    @EventListener
    @Async
    public void handle(ContextClosedEvent event) {
        log.info(Thread.currentThread().toString());
        log.info("ContextClosedEvent");
    }

}