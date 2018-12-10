package me.ninjasul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ApplicationRunnerHandler implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRunnerHandler.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // VM 옵션은 ApplicationArgument 가 아님
        // ApplicationArguments 는 추상화 API들을 제공하므로 CommandLineRunner 보다는 ApplicationRunner를 구현하는 편이 낫다.
        LOGGER.info(" foo: " + args.containsOption("foo") );
        LOGGER.info(" bar: " + args.containsOption("bar") );
    }
}