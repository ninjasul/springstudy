package me.ninjasul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NinjasulSpringBootGettingStartedApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(NinjasulSpringBootGettingStartedApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

	/*@Bean
	public Holoman holoman() {
		Holoman holoman = new Holoman();
		holoman.setName("ninjasul2");
		holoman.setHowLong(60);
		return holoman;
	}*/
}
