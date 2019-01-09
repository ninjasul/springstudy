package chap16_springtest.bank.service;

import chap16_springtest.bank.dao.AccountDao;
import chap16_springtest.bank.domain.Account;
import chap16_springtest.bank.exceptions.InsufficientBalanceException;
import chap16_springtest.bank.service.AccountService;
import chap16_springtest.bank.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class AccountServiceImplMockTest {

    private static final String TEST_ACCOUNT_NO = "1234";

    private AccountDao accountDao;
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        accountDao = mock(AccountDao.class);
        accountService = new AccountServiceImpl(accountDao);
    }

    @Test
    public void createAccount() {
        // Given & When
        accountService.createAccount(TEST_ACCOUNT_NO);

        // Then
        verify( accountDao, times(1)).createAccount(any(Account.class));
    }

    @Test
    public void removeAccount() {
        // Given
        Account account = buildAccount();

        // When
        when( accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);
        accountService.removeAccount(TEST_ACCOUNT_NO);


        // Then
        verify( accountDao, times(1)).findAccount(TEST_ACCOUNT_NO);
        verify( accountDao, times(1)).removeAccount(account);
    }

    @Test
    public void deposit() {
        // Given
        Account account = buildAccount();

        // When
        when( accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);
        accountService.deposit(TEST_ACCOUNT_NO, 50);

        // Then
        verify( accountDao, times(1)).findAccount(any(String.class));
        verify( accountDao, times(1)).updateAccount(account);
    }

    @Test
    public void withdrawWithSufficientBalance() {
        // Given
        Account account = buildAccount();

        // When
        when( accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);
        accountService.withdraw(TEST_ACCOUNT_NO, 50);

        // Then
        verify( accountDao, times(1)).findAccount(any(String.class));
        verify( accountDao, times(1)).updateAccount(account);
    }

    @Test(expected = InsufficientBalanceException.class)
    public void withdrawWithInsufficientBalance() {
        // Given
        Account account = buildAccount();

        // When
        when( accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

        // Do
        accountService.withdraw(TEST_ACCOUNT_NO, 150);
    }

    @Test
    public void getBalance() {
        // Given
        Account account = buildAccount();

        // When
        when( accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);
        accountService.getBalance(TEST_ACCOUNT_NO);

        // Then
        verify( accountDao, times(1)).findAccount(TEST_ACCOUNT_NO);
    }

    private Account buildAccount() {
        return Account.builder()
                .accountNo(TEST_ACCOUNT_NO)
                .balance(100)
                .build();
    }
}