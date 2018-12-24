package me.ninjasul.restapi.accounts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @Test
    public void findByUserName() {
        // Given
        String email = "ninjasul@emails.com";
        String password = "ninjasul";

        Account account = Account.builder()
                            .email(email)
                            .password(password)
                            .build();

        accountRepository.save(account);

        // When
        UserDetailsService userDetailsService = accountService;
        UserDetails userDetails = accountService.loadUserByUsername(email);

        // Then
        assertThat(password).isEqualTo(userDetails.getPassword());
    }
}