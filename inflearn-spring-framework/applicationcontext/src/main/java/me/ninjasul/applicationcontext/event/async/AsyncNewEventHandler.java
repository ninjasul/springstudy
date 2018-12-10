package me.ninjasul.applicationcontext.event.async;

import org.springframework.context.event.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncNewEventHandler {

    @EventListener
    @Async
    public void handle(AsyncNewEvent event) {
        System.out.printf("[%s][%s]: Received an event. The data is %s\n", Thread.currentThread().toString(), this.getClass().getSimpleName(), event.getData());
    }


    /**
     * ApplicationContext 를 초기화 했거나 리프레쉬 했을 때 발생
     * @param event
     */
    @EventListener
    @Async
    public void handle(ContextRefreshedEvent event) {
        System.out.printf("[%s][%s]: %s\n", Thread.currentThread().toString(), this.getClass().getSimpleName(), event.getClass().getSimpleName());
    }

    /**
     * ApplicationContext를 start() 하여 라이프사이클 빈들이 시작 신호를 받은 시점에 발생
     * @param event
     */
    @EventListener
    @Async
    public void handle(ContextStartedEvent event) {
        System.out.printf("[%s][%s]: %s\n", Thread.currentThread().toString(), this.getClass().getSimpleName(), event.getClass().getSimpleName());
    }

    /**
     * ApplicationContext를 stop() 하여 라이프사이클 빈들이 정지 신호를 받은 시점에 발생.
     * @param event
     */
    @EventListener
    @Async
    public void handle(ContextStoppedEvent event) {
        System.out.printf("[%s][%s]: %s\n", Thread.currentThread().toString(), this.getClass().getSimpleName(), event.getClass().getSimpleName());
    }

    /**
     * ApplicationContext를 close() 하여 싱글톤 빈이 소멸되는 시점에 발생.
     * @param event
     */
    @EventListener
    @Async
    public void handle(ContextClosedEvent event) {
        System.out.printf("[%s][%s]: %s\n", Thread.currentThread().toString(), this.getClass().getSimpleName(), event.getClass().getSimpleName());
    }
}