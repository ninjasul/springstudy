package com.ninjasul.springframework._10_messagesource;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@Log4j2
public class MessageSourceApplication {

    @Autowired
    private ConfigurableEnvironment env;

    public static void main(String[] args) {
        SpringApplication.run(MessageSourceApplication.class, args);
    }

    @Bean
    public MessageSource messageSource() {
        //env.setActiveProfiles(AppEnv.STAGING);
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages/" + getFirstActiveProfile() + "/messages");
        log.info("basename: {}", messageSource.getBasenameSet());
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

    private String getFirstActiveProfile() {
        return ( env.getActiveProfiles() == null || env.getActiveProfiles().length <= 0 ) ? "prod" :  env.getActiveProfiles()[0];
    }
}

