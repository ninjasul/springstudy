package com.ninjasul.springframework._10_messagesource;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Log4j2
public class AppRunner implements ApplicationRunner {

    @Autowired
    private MessageSource messageSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        while( true ) {
            log.info("greetings: {}", messageSource.getMessage("greetings", new Object[]{"ninjasul"}, Locale.getDefault()));
            log.info("greetings ko_kr: {}", messageSource.getMessage("greetings", new Object[]{"ninjasul"}, Locale.KOREA));
            Thread.sleep(3000);
        }
    }
}