package chap16_springtest.bank.service;

import chap16_springtest.bank.config.BankConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;
import static org.springframework.jdbc.datasource.init.ScriptUtils.executeSqlScript;

@ContextConfiguration(classes = BankConfiguration.class)
public class AccountServiceJdbcTemplateTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String TEST_ACCOUNT_NO = "1234";

    @Autowired
    private AccountService accountService;

    @Before
    public void setUp() {
        executeSqlScript("classpath:/bank.sql", true);
        jdbcTemplate.update("INSERT INTO ACCOUNT (ACCOUNT_NO, BALANCE) VALUES (?, ?)", TEST_ACCOUNT_NO, 100);
    }

    @Test
    public void deposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        double balance = getBalance(TEST_ACCOUNT_NO);
        assertEquals(150.0, balance, 0);
    }

    @Test
    public void withDraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        double balance = getBalance(TEST_ACCOUNT_NO);
        assertEquals(50.0, balance, 0);
    }

    private double getBalance(String accountNo) {
        return jdbcTemplate.queryForObject("SELECT BALANCE FROM ACCOUNT WHERE  ACCOUNT_NO = ?", Double.class, accountNo);
    }
}