package me.ninjasul.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateRunner.class);

    @Autowired
    private RestTemplateBuilder builder ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RestTemplate restTemplate = builder.build();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("/hello");

        String helloResult = restTemplate.getForObject( "http://localhost:8080//hello", String.class);
        logger.info(helloResult);

        stopWatch.stop();

        stopWatch.start("/world");
        String worldResult = restTemplate.getForObject( "http://localhost:8080/world", String.class );
        logger.info(worldResult);

        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }
}