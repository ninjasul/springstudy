package me.ninjasul.restapi.accounts;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

    @Test(expected = UsernameNotFoundException.class)
    public void findByUserNameNotFoundUserException1() {
        String username = "random@email.com";
        accountService.loadUserByUsername(username);
    }

    @Test
    public void findByUserNameNotFoundUserException2() {

        // Given
        String username = "random@email.com";
        try {
            // When
            accountService.loadUserByUsername(username);
            fail("supposed to be filed");
        } catch( Exception e ) {
            // Then
            assertThat(e instanceof UsernameNotFoundException);
            assertThat(e.getMessage()).contains(username);
        }

    }

    @Test
    public void findByUserNameNotFoundUserException3() {

        // Given
        String username = "random@email.com";

        // Then
        expectedException.expect(UsernameNotFoundException.class);
        expectedException.expectMessage(Matchers.containsString(username));

        // When
        accountService.loadUserByUsername(username);
    }
}