package chap16_springtest.bank.service;

import chap16_springtest.bank.config.BankConfiguration;
import chap16_springtest.bank.service.AccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= BankConfiguration.class)
public class AccountServiceJUnit4ContextTests implements ApplicationContextAware {

    private static final String TEST_ACCOUNT_NO = "1234";
    private ApplicationContext applicationContext;
    private AccountService accountService;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Before
    public void setUp() throws Exception {
        accountService = applicationContext.getBean(AccountService.class);
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

    @After
    public void cleanup() {
        accountService.removeAccount(TEST_ACCOUNT_NO);
    }
}