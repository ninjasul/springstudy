package me.ninjsul.springsecurityoauth2.config;

import me.ninjsul.springsecurityoauth2.domain.enums.SocialType;
import me.ninjsul.springsecurityoauth2.oauth.ClientResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.OAuth2ClientConfiguration;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

import static me.ninjsul.springsecurityoauth2.domain.enums.SocialType.*;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        http
            .authorizeRequests()
                .antMatchers("/", "/login/**", "/css/**", "/images/**", "/js/**", "/console/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
            .and()
                // XFrameOptionsHeaderWriter의 최적화 설정을 허용하지 않음.
                .headers().frameOptions().disable()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
            .and()
                .formLogin()
                // 폼 로그인에 성공하면 포워딩할 경로
                .successForwardUrl("/board/list")
            .and()
                .logout()
                // 로그아웃 URL
                .logoutUrl("/logout")
                // 로그아웃 성공시 포워딩 URL
                .logoutSuccessUrl("/")
                // 로그아웃 성공 시 삭제할 쿠키값
                .deleteCookies("JSESSIONID")
                // 세션 무효화 처리
                .invalidateHttpSession(true)
            .and()
                // 첫 번째 인자보다 먼저 시작될 필터를 등록
                // 문자 인코딩 필터보다 CsrfFilter를 먼저 실행
                .addFilterBefore(filter, CsrfFilter.class)
                .addFilterBefore(oauth2Filter(), BasicAuthenticationFilter.class)
                .csrf().disable();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    private Filter oauth2Filter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(oauth2Filter(facebook(), "/login/facebook", FACEBOOK));
        filters.add(oauth2Filter(google(), "/login/google", GOOGLE));
        filters.add(oauth2Filter(kakao(), "/login/kakao", KAKAO));
        filter.setFilters(filters);
        return filter;
    }

    private Filter oauth2Filter(ClientResources client, String path, SocialType socialType) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oAuth2ClientContext);
        filter.setRestTemplate(template);
        filter.setTokenServices(new UserTokenService(client, socialType));
        filter.setAuthenticationSuccessHandler((request, response, authentication) -> response.send)

    }

    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("google")
    public ClientResources google() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("kakao")
    public ClientResources kakao() {
        return new ClientResources();
    }
}