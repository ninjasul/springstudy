package com.ninjasul.springframework.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnvironmentApplication {
    public static final String TEST = "test";
    public static final String STAGING = "staging";

    public static void main(String[] args) {
        SpringApplication.run(EnvironmentApplication.class, args);
    }

}

