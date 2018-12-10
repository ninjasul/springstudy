package me.ninjasul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**********************************************************************************************************************
 * ApplicationStartingEvent 는 Application Context 가 생성되기 이전에 발생하는 이벤트이기 때문에
 * Bean 객체로 등록이 되지 않음.
 * main 함수 내에서 SpringApplication.addListner() 메소드 호출을 통해 이벤트를 수동으로 등록 해 주어야 함.
 *********************************************************************************************************************/
@Component
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartingEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        System.out.println("=======================");
        System.out.println("Application is starting");
        System.out.println("=======================");
    }
}