package com.ninjasul.spring;

import com.ninjasul.spring.domain.JournalEntry;
import com.ninjasul.spring.repository.JournalRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/***********************************************************************************************************************
 @SpringBootConfiguration
 @EnableAutoConfiguration : 스프링 부트 auto configuration 을 위한 어노테이션
 - spring-boot-starter-web 스타터가 선언되어 있으면	스프링 부트는 알아서 웹 애플리케이션을 구성
 - @RequestMapping 이 붙은 메소드를 가지고 있고 @Controller 어노테이션이 달린 클래스를
 웹 컨트롤러로 간주하여 tomcat 서버 기동 시 클라이언트가 접속할 수 있도록 처리.
 - AutoConfigurationImportSelector 클래스를 통해 빈을 유추하여 자동 구성
 @ComponentScan
 ***********************************************************************************************************************/
@SpringBootApplication
public class SpringBootJournal2Application {
	@Bean
    InitializingBean saveData(JournalRepository repo) {
		return () -> {
			repo.save(new JournalEntry("스프링 부트 입문", "오늘부터 스프링 부트 공부하기 시작했다.", "2016-01-02"));
			repo.save(new JournalEntry("간단한 스프링 부트 프로젝트", "스프링 부트 프로젝트를 처음 만들어보았다.", "2016-01-03"));
			repo.save(new JournalEntry("스프링 부트 해부", "스프링 부트를 자세히 살펴보았다.", "2016-02-01"));
			repo.save(new JournalEntry("스프링 부트 클라우드", "클라우드 파운드리를 응용한 스프링부트를 공부했다.", "2016-03-01"));
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJournal2Application.class, args);
	}
}