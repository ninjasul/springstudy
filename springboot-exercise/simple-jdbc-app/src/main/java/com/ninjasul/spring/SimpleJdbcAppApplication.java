package com.ninjasul.spring;

import com.ninjasul.spring.service.JournalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleJdbcAppApplication  implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(SimpleJdbcAppApplication.class);

    @Autowired
    JournalService service;

	public static void main(String[] args) {
		SpringApplication.run(SimpleJdbcAppApplication.class, args);
	}

	@Override
	public void run(String... args0) throws Exception {
		logger.info("@@ 데이터 생성....");
		service.insertData();
		logger.info("@@ findAll() 호출...");
		service.findAll().forEach(entry -> logger.info(entry.toString()));
	}
}
