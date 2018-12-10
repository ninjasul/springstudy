package com.ninjasul.spring;

import ch.qos.logback.core.util.FixedDelay;
import com.ninjasul.spring.rabbitmq.Producer;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@EnableScheduling
@SpringBootApplication
public class SpringBootRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRabbitmqApplication.class, args);
	}

	@Value("${myqueue}")
	String queue;

	@Bean
	Queue queue() {
		// 보존 여부는 false 이므로 application 재기동 시 큐는 사라짐.
		return new Queue(queue, false);
	}
/*
	@Bean
	CommandLineRunner sender(Producer producer) {
		return args -> {
			producer.sendTo( queue, "안녕하세요, 여러분!");
		};
	}*/

	@Autowired
	Producer producer;

	@Scheduled(fixedDelay=500L)
	public void sendMessages() {
		producer.sendTo( queue, "안녕하세요, 여러분! 지금 시각은 " + new Date());
	}

}
