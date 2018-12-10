/*
package com.ninjasul.com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.List;

@SpringBootApplication
public class SpringBootSimpleApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootSimpleApplication.class);

    public static void main(String[] args) {
        //Logger LOGGER = LoggerFactory.getLogger(SpringBootSimpleApplication.class);
        */
/*
        SpringApplication app = new SpringApplication(SpringBootSimpleApplication.class);
        app.setBanner(new Banner() {
            @Override
            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                out.print("\n\n\t나만의 멋진 배너랍니다!\n\n".toUpperCase());
            }
        });
        app.run(args);
        *//*



        // 이벤트 리스너 생성
*/
/*
        new SpringApplicationBuilder(SpringBootSimpleApplication.class).listeners(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                LOGGER.info("#### > " + event.getClass().getCanonicalName());
            }
        }).run(args);
*//*



        // 애플리케이션 인자 접근
        SpringApplication.run(SpringBootSimpleApplication.class, args);
    }

    @Component
    class MyComponent {
        @Autowired
        public MyComponent(ApplicationArguments args) {
            boolean enable = args.containsOption("enable");

            if( enable ) {
                LOGGER.info("## > turned on 'enable'");
            }

            List<String> _args = args.getNonOptionArgs();
            LOGGER.info("## > other arguments...");

            if( !_args.isEmpty() ) {
                _args.forEach(file -> LOGGER.info(file));
            }
        }
    }
}
*/
