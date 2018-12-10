package me.ninjasul;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

import javax.swing.*;
import java.io.PrintStream;

@SpringBootApplication
public class NinjasulSpringBootInitApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication( NinjasulSpringBootInitApplication.class);

		application.setWebApplicationType(WebApplicationType.NONE);

		// ApplicationStartingEvent 는 Application Context 가 생성되기 이전에 발생하는 이벤트이기 때문에 아래와 같이 수동 등록.
		application.addListeners(new ApplicationStartingEventListener());

/*

		// 아래 코드 보다 resource 배너가 우선임.
		application.setBanner((environment, sourceClass, out ) -> {
			out.println("================================================");
			out.println(" 					NINJASUL 					 ");
			out.println("================================================");
		});
*/

		//application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

/*
	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(NinjasulSpringBootInitApplication.class)
				.banner(new Banner() {
					@Override
					public void printBanner(Environment environment, Class<?> aClass, PrintStream printStream) {
						printStream.println("================================================");
						printStream.println(" 		SpringApplicationBuilder() NINJASUL		 ");
						printStream.println("================================================");
					}
				})
				.run(args);
	}*/
}
