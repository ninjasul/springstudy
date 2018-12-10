package me.ninjasul.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AopApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run();
    }
}
