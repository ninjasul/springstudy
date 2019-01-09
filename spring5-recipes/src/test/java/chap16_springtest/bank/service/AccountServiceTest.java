package chap16_springtest.bank.service;

import chap16_springtest.bank.dao.InMemoryAccountDao;
import chap16_springtest.bank.service.AccountService;
import chap16_springtest.bank.service.AccountServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest {
    
    private static final String TEST_ACCOUNT_NO = "1234";
    
    private AccountService accountService;


    @Before
    public void setUp() throws Exception {
        accountService = new AccountServiceImpl(new InMemoryAccountDao());
        accountService.createAccount(TEST_ACCOUNT_NO);
        accountService.deposit( TEST_ACCOUNT_NO,100);
    }

    @Test
    public void deposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        assertEquals( 150.0, accountService.getBalance(TEST_ACCOUNT_NO), 0);
    }

    @Test
    public void withdraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        assertEquals( 50.0, accountService.getBalance(TEST_ACCOUNT_NO), 0);
    }


    @After
    public void cleanup() {
        accountService.removeAccount(TEST_ACCOUNT_NO);
    }
}