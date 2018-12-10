package com.ninjasul.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChildConfig extends SuperConfig {
    @Bean
    public String world() {
        return "world";
    }
}
