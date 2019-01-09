package chap16_springtest.bank.service;

import chap16_springtest.bank.config.BankConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= BankConfiguration.class)
public class AccountServiceJUnit4ContextTests3 {

    private static final String TEST_ACCOUNT_NO = "1234";

    @Autowired
    private AccountService accountService;

    @Before
    public void setUp() {
        accountService.createAccount(TEST_ACCOUNT_NO);
        accountService.deposit(TEST_ACCOUNT_NO, 100);
    }

    @Test
    public void deposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        assertEquals(150.0, accountService.getBalance(TEST_ACCOUNT_NO), 0);
    }

    @Test
    public void withDraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        assertEquals(50.0, accountService.getBalance(TEST_ACCOUNT_NO), 0);
    }
}