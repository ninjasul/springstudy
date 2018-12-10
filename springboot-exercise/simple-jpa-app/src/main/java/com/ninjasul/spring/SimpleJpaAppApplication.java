package com.ninjasul.spring;

import com.ninjasul.spring.service.JournalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleJpaAppApplication {

	private static final Logger logger = LoggerFactory.getLogger(SimpleJpaAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SimpleJpaAppApplication.class, args);
	}


	/*******************************************************************************************************************
	 * @param service
	 * @return CommandLineRunner
	 * @description @Bean 을 붙인 start 메서드는 CommandLineRunner 인터페이스를 반환함. 앱 시동 직후 스프링 부트가
	 *              실행할 메서드를 전달할 의도로 생성.
     ******************************************************************************************************************/
	@Bean
	CommandLineRunner start(JournalService service) {
		return args -> {
			logger.info("@@ 데이터 생성....");
			//service.insertData();
			logger.info("@@ findAll() 호출...");
			service.findAll().forEach(entry -> logger.info(entry.toString()));
		};
	}
}
