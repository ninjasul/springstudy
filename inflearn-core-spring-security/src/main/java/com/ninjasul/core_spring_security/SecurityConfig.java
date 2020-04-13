package com.ninjasul.core_spring_security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated();


        http.formLogin();
        /*http.formLogin()
            //.loginPage("/loginPage")
            .defaultSuccessUrl("/")
            .failureUrl("/login")
            .usernameParameter("userId")
            .passwordParameter("passwd")
            .loginProcessingUrl("/login_proc")
            .successHandler((request, response, authentication) -> {
                log.info("authentication success: {}", authentication.getName());
                response.sendRedirect("/");
            })
            .failureHandler((request, response, exception) -> {
                log.info("exception: {}", exception.getMessage());
                response.sendRedirect("/login");
            })
            .permitAll();   // /loginPage 자체는 인증을 받지 않아도 되게 끔 설정.*/


        http.logout()
            .logoutUrl("/doLogout")
            .logoutSuccessUrl("/login")
            .addLogoutHandler((request, response, authentication) -> {
                HttpSession session = request.getSession();
                session.invalidate();
            })
            .logoutSuccessHandler((request, response, authentication) -> {
                response.sendRedirect("/login");
            })
            .deleteCookies("remember-me");

    }
}
