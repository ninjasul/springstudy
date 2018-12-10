package com.ninjasul.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration: 구성 클래스 임을 나타냄.
// @EnableGlobalAuthentication:AuthenticationManagerBuilder 의 전역 인스턴스를 구성하며 앱에 있는 모든 빈에 보안을 적용
/*
@Configuration
@EnableGlobalAuthentication
*/
public class InMemorySecurityConfiguration {

    // AuthenticationManagerBuilder를 자동 연결하는 메서드
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails userDetails1 = User.withUsername("user")
            .password(encoder.encode("password"))
            .roles("USER")
            .build();

        UserDetails userDetails2 = User.withUsername("admin")
                .password(encoder.encode("password"))
                .roles("USER", "ADMIN")
                .build();

        auth.inMemoryAuthentication().withUser(userDetails1).withUser(userDetails2);

        // Spring Boot 2.X 대에서는 아래의 방법을 사용할 수 없음.
        //auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin").password("password").roles("USER", "ADMIN");
    }
}