package com.ninjasul.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.ResultSet;

// WebSecurityConfigurerAdapter:  웹 어플리케이션 리소스를 구성하는 여러 방법 중 하나임.
//                                보통 configure(HttpSecurity) 와 configure(AuthenticationManagerBuilder) 메서드를
//                                재정의 해야 하지만 이 예제에서는 JdbcSecurityConfiguration 에서 GlobalAuthenticationConfigurerAdapter 에서
//                                init(AuthenticationManagerBuilder) 메서드를 재정의한 상태이기 때문에 configure(HttpSecurity) 메서드 하나만 재정의하면 됨.
@Configuration
@EnableGlobalAuthentication
public class ResourceSecurityConfiguration extends WebSecurityConfigurerAdapter {

    // WebSecurityConfigurerAdapter의 메서드를 재정의하여 접근을 제한할 리소스를 지정함.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/api/**").authenticated()
            .and()
            //.httpBasic();
            //.formLogin();

            // 커스텀 로그인 페이지 접속
            .formLogin().loginPage("/login").permitAll()
            .and()
            .logout().permitAll();
    }


}