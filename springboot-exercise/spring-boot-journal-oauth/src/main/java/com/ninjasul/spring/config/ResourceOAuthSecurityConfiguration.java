package com.ninjasul.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/***********************************************************************************************************************
 @EnableAuthorizationServer:  OAuth2 인증 서버를 구성.
                              인증 endpoint(/oauth/authorize)은 개발자가 직접 보안을 설정.
                              토큰 endpoint(/oauth/token)는 사용자 크레덴셜에 따라 HTTP 기본 인증 방식으로 보안이 자동 적용.

 @EnableResourceServer: 리소스 서버를 구성. 알맞은 OAuth2 토큰을 보낸 요청만 통과시키는 스프링 시큐리티 필터를 켬.

 ResourceServerConfigurerAdapter: ResourceServerConfigurer 인터페이스 구현용 클래스


 **********************************************************************************************************************/
@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class ResourceOAuthSecurityConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // api 리소스에 락을 검.
        http.authorizeRequests()
            .antMatchers("/").permitAll()               // 메인페이지는 인증 없이 접속 가능
            .antMatchers("/api/**").authenticated();    // /api URL은 OAuth2로 보호
   }
}