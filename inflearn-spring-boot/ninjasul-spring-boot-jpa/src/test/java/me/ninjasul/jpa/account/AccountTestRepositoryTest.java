package me.ninjasul.jpa.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
// @DataJpaTest를 사용하면 Repository 에 대한 슬라이스 테스트를 사용하는 것이므로 인메모리 DB를 사용하게 됨.
@DataJpaTest
public class AccountTestRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountTestRepositoryTest.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() throws SQLException {

        try(Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            logger.info("URL: " + metaData.getURL());
            logger.info("DriverName: " + metaData.getDriverName());
            logger.info("UserName: " + metaData.getUserName());

            Account account = new Account();
            account.setUsername("ninjasul");
            account.setPassword("ninjasul");

            Account newAccount = accountRepository.save(account);
            assertThat(newAccount).isNotNull();

            Optional<Account> existingAccount = accountRepository.findByUsername(newAccount.getUsername());
            assertThat(existingAccount).isNotEmpty();

            Optional<Account> nonExistingAccount = accountRepository.findByUsername("ninjasul2");
            assertThat(nonExistingAccount).isEmpty();
        }
    }
}