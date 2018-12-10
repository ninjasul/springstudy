package com.ninjasul.spring;

import com.ninjasul.spring.domain.Journal;
import com.ninjasul.spring.repository.JournalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleMongoAppApplication {
	private static final Logger logger = LoggerFactory.getLogger(SimpleMongoAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SimpleMongoAppApplication.class, args);
	}

	@Bean
	CommandLineRunner start(JournalRepository repository) {
		return args -> {
			logger.info("> 기존 데이터 삭제 ... ");
			repository.deleteAll();
			logger.info("> 데이터를 새로 생성 ... ");
			repository.save(new Journal("스프링 부트 입문", "오늘부터 스프링 부트 공부하기 시작했다", "01/01/2016"));
			repository.save(new Journal("간단한 스프링 부트 프로젝트", "스프링 부트 프로젝트를 처음 만들어보았다", "01/02/2016"));
			repository.save(new Journal("스프링 부트 해부", "스프링 부트률 자세히 살펴보았다", "02/01/2016"));
			repository.save(new Journal("스프링 부트 클라우드", " 클라우드 파운드리를 응용한 스프링부트를 공부했다", "03/01/2016"));
			logger.info("> 전체 데이터 조회...");
			repository.findAll().forEach(entry -> logger.info(entry.toString()));
			logger.info("> LIKE로 데이터 조회...");
			repository.findByTitleLike("Cloud").forEach(entry -> logger.info(entry.toString()));
		};
	}
}
