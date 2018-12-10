package me.ninjasul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerHandler implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRunnerHandler.class);

    /*
    @Value("${ninjasul.fullname}")
    private String fullname;

    @Value("${ninjasul.age}")
    private int age;

    @Value("${ninjasul.name}")
    private String name;
    */

    @Autowired
    MyProperties myProperties;

    @Autowired
    private String hello;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        LOGGER.debug("=========================================");
/*
        LOGGER.info(fullname);
        LOGGER.info("" + age);
*/
        LOGGER.debug(myProperties.getFullName());
        LOGGER.debug(myProperties.getDbName());
        LOGGER.debug("" + myProperties.getAge());
        LOGGER.debug("" + myProperties.getSessionTimeout());
        LOGGER.debug(hello);
        LOGGER.debug("=========================================");
    }
}