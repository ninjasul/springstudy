package me.ninjasul.nullsafety;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NullsafetyApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(NullsafetyApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run();
    }
}
