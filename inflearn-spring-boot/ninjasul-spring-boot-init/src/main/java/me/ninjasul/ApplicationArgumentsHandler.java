package me.ninjasul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class ApplicationArgumentsHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartedEventListener.class);

    public ApplicationArgumentsHandler(ApplicationArguments arguments) {

        // VM 옵션은 ApplicationArgument 가 아님
        LOGGER.info(" foo: " + arguments.containsOption("foo") );
        LOGGER.info(" bar: " + arguments.containsOption("bar") );
    }
}

