package com.ninjasul.spring;

import com.ninjasul.spring.domain.JournalEntry;
import com.ninjasul.spring.repository.JournalRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootJournalSecureApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJournalSecureApplication.class, args);
	}
}
