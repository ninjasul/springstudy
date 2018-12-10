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

import java.util.Arrays;

@SpringBootApplication
public class SpringBootSimpleApplication3 implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootSimpleApplication3.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSimpleApplication3.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
        LOGGER.info("Non Option Args: {}", args.getNonOptionArgs());
        LOGGER.info("Option Names: {}", args.getOptionNames());

        for (String name : args.getOptionNames()) {
            LOGGER.info("arg-" + name + "=" + args.getOptionValues(name));
        }
    }
}
*/
