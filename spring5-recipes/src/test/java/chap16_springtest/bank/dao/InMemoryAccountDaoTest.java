package chap16_springtest.bank.dao;

import chap16_springtest.bank.dao.InMemoryAccountDao;
import chap16_springtest.bank.domain.Account;
import chap16_springtest.bank.exceptions.AccountNotFoundException;
import chap16_springtest.bank.exceptions.DuplicateAccountException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InMemoryAccountDaoTest {

    private static final String EXISTING_ACCOUNT_NO = "1234";
    private static final String NEW_ACCOUNT_NO = "5678";

    private Account existingAccount;
    private Account newAccount;
    private InMemoryAccountDao accountDao;


    @Before
    public void setUp() {
        existingAccount = new Account(EXISTING_ACCOUNT_NO, 100);
        newAccount = new Account(NEW_ACCOUNT_NO, 200);
        accountDao = new InMemoryAccountDao();
        accountDao.createAccount(existingAccount);
    }

    @Test
    public void accountExists() {
        assertTrue(accountDao.accountExists(EXISTING_ACCOUNT_NO));
        assertFalse(accountDao.accountExists(NEW_ACCOUNT_NO));
    }

    @Test
    public void createAccount() {
        accountDao.createAccount(newAccount);
        assertEquals( accountDao.findAccount(NEW_ACCOUNT_NO), newAccount );
    }

    @Test(expected = DuplicateAccountException.class)
    public void createDuplicateAccount() {
        accountDao.createAccount(existingAccount);
    }


    @Test(expected = AccountNotFoundException.class)
    public void updateNotExistingAccount() {
        accountDao.updateAccount(newAccount);
    }

    @Test
    public void removeExistingAccount() {
        accountDao.removeAccount(existingAccount);
        assertFalse(accountDao.accountExists(EXISTING_ACCOUNT_NO));
    }

    @Test(expected=AccountNotFoundException.class)
    public void removeNotExistingAccount() {
        accountDao.removeAccount(newAccount);
    }

    @Test
    public void findExistingAccount() {
        Account account = accountDao.findAccount(EXISTING_ACCOUNT_NO);
        assertEquals( existingAccount, account );
    }

    @Test(expected = AccountNotFoundException.class)
    public void findNotExistingAccount() {
        accountDao.findAccount(NEW_ACCOUNT_NO);
    }
}