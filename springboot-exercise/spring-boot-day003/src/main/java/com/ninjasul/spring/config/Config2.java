package com.ninjasul.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config2 {
    @Bean
    public String world1() {
        return "import world";
    }
}
