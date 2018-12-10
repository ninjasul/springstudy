package me.ninjasul.jpa.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
// @SpringBootTest를 사용하면 슬라이스가 아닌 실제 빈이 등록되며 인메모리 DB가 아닌 MySQL DB를 사용하게 됨.
// 테스트 DB 주소를 입력하고 싶으면 @SpringBootTest(properties = "spring.datasource.url=''") 이런식으로 설정하면 됨.
@SpringBootTest
public class AccountRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryTest.class);

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