package me.ninjasul.applicationcontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Locale;

@SpringBootApplication
@PropertySource("classpath:/properties/${spring.profiles.active}/application.properties")
@EnableAsync
public class ApplicationContextApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(ApplicationContextApplication.class, args);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(3);
        return messageSource;
    }
}
