package com.ninjasul.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Import(Config2.class)
@Configuration
public class Config1 {
    @Bean
    String hello1() {
        return "import hello";
    }
}
