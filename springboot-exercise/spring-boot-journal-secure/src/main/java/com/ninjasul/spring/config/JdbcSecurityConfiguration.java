package com.ninjasul.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.ResultSet;

// GlobalAuthenticationConfigurerAdapter: SecurityConfigurer 인터페이스를 구현하므로 init 메서드를 override 해야 함.
@Configuration
@EnableGlobalAuthentication
public class JdbcSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public UserDetailsService userDetailsService( JdbcTemplate jdbcTemplate ) {
        RowMapper<User> userRowMapper = (ResultSet rs, int i ) ->
            new User(
                rs.getString("ACCOUNT_NAME"),                                       // username

                // spring-security 5 버전 이상부터는 password encoder를 무조건 사용해야 함.
                encoder.encode(rs.getString("PASSWORD")),                           // password
                rs.getBoolean("ENABLED"),                                           // enabled
                rs.getBoolean("ENABLED"),                                           // accountNonExpired
                rs.getBoolean("ENABLED"),                                           // credentialNonExpired
                rs.getBoolean("ENABLED"),                                           // accountNonLocked
                AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN")       // authorities
            );

        return userName -> jdbcTemplate.queryForObject("SELECT * from ACCOUNT where ACCOUNT_NAME = ?", userRowMapper, userName );
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}