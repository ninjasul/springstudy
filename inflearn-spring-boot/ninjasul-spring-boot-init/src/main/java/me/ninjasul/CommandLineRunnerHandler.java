package me.ninjasul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(1)
public class CommandLineRunnerHandler implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineRunnerHandler.class);

    @Override
    public void run(String... args) throws Exception {

        for( String curArg : args ) {
            LOGGER.info(curArg);
        }
    }
}