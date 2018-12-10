/*
package com.ninjasul.com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootSimpleApplication4 {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootSimpleApplication4.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSimpleApplication4.class, args);
    }

    @Value("${myapp.server-ip}")
    String serverIp;

    @Bean
    CommandLineRunner values() {
        return args -> {
            LOGGER.info(" > 서버 IP: " + serverIp );
        };
    }
}
*/
