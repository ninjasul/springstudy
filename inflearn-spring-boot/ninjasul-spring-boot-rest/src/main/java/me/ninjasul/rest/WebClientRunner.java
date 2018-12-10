package me.ninjasul.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//@Component
public class WebClientRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(WebClientRunner.class);

    @Autowired
    WebClient.Builder builder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        WebClient webClient = builder.build();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Mono<String> helloMono = webClient.get().uri("/hello").retrieve().bodyToMono(String.class);

        helloMono.subscribe( s -> {
            printTimeStamp( stopWatch, s, "/hello" );
        });

        Mono<String> worldMono = webClient.get().uri("/world").retrieve().bodyToMono(String.class);

        worldMono.subscribe( s -> {
            printTimeStamp( stopWatch, s, "/world" );
        });


    }

    private void printTimeStamp( StopWatch stopWatch, String s, String taskName ) {
        logger.info(s);

        if( stopWatch.isRunning() ) {
            stopWatch.stop();
        }

        logger.info(stopWatch.prettyPrint());
        stopWatch.start(taskName);
    }
}