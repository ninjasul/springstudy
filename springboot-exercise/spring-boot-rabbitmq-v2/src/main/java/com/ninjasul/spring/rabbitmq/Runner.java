package com.ninjasul.spring.rabbitmq;

import com.ninjasul.spring.SpringBootRabbitmqV2Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner( Receiver receiver, RabbitTemplate rabbitTemplate ) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("Sending message...");
        // "foo.bar.baz" 는 exchange 의 routing key임
        rabbitTemplate.convertAndSend( SpringBootRabbitmqV2Application.TOPIC_EXCHANGE_NAME, "foo.bar.baz", "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}