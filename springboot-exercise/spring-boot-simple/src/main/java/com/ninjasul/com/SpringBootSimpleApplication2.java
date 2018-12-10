/*
package com.ninjasul.com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootSimpleApplication2 implements CommandLineRunner, ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootSimpleApplication2.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSimpleApplication2.class, args);
    }

    @Bean
    String info() {
        return "It's just a simple String bean.";
    }

    @Autowired
    String info;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("## > ApplicationRunner 구현체...");
        LOGGER.info("info 빈에 액세스: " + info);
        args.getNonOptionArgs().forEach(file -> LOGGER.info(file));
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("## > CommandLineRunner 구현체...");
        LOGGER.info("info 빈에 액세스: " + info);

        for (String arg : args) {
            LOGGER.info(arg);
        }
    }
}
*/
