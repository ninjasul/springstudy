package com.ninjasul.spring.config;

import org.springframework.context.annotation.Bean;

public class SuperConfig {
    @Bean
    public String hello() {
        return "hello";
    }
}
