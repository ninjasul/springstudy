package me.ninjasul.restapi.config;

import me.ninjasul.restapi.accounts.Account;
import me.ninjasul.restapi.accounts.AccountRole;
import me.ninjasul.restapi.accounts.AccountService;
import me.ninjasul.restapi.common.BaseControllerTest;
import me.ninjasul.restapi.common.TestDescription;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @TestDescription("액세스 토큰 발급 테스트")
    public void getAuthToken() throws Exception {

        String username = "ninjasul2@email.com";
        String password = "ninjasul2";
        Account account = Account.builder()
                                .email(username)
                                .password(password)
                                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                            .build();
        accountService.saveAccount(account);

        String clientId = "myApp";
        String clientSecret = "pass";

        mockMvc.perform(post("/oauth/token")
                        .with(httpBasic(clientId, clientSecret))
                        .param("username", username)
                        .param("password", password)
                        .param("grant_type", "password")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists())
        ;
    }
}