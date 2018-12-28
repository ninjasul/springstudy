package me.ninjasul.restapi.config;

import me.ninjasul.restapi.accounts.Account;
import me.ninjasul.restapi.accounts.AccountRole;
import me.ninjasul.restapi.accounts.AccountService;
import me.ninjasul.restapi.common.AppProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    /**
     * 패스워드에 인코딩 방식 문자열을 접두어로 삽입하는 기본 PasswordEncoder 를 생성함.
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ApplicationRunner applicationRunner() {

        return new ApplicationRunner() {
            @Autowired
            AccountService accountService;

            @Autowired
            AppProperties appProperties;

            @Override
            public void run(ApplicationArguments args) throws Exception {

                Account admin = Account.builder()
                                .email(appProperties.getAdminUsername())
                                .password(appProperties.getAdminPassword())
                                .roles(Set.of(AccountRole.USER, AccountRole.ADMIN))
                                .build();

                accountService.saveAccount(admin);

                Account user = Account.builder()
                        .email(appProperties.getUserUsername())
                        .password(appProperties.getUserPassword())
                        .roles(Set.of(AccountRole.USER))
                        .build();

                accountService.saveAccount(user);
            }
        };
    }
}