package chap16_springtest.bank.dao;

import chap16_springtest.bank.domain.Account;
import chap16_springtest.bank.exceptions.AccountNotFoundException;
import chap16_springtest.bank.exceptions.DuplicateAccountException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAccountDao implements AccountDao {

    private Map<String, Account> accounts;

    public InMemoryAccountDao() {
        this.accounts = new HashMap<String, Account>();
    }

    public boolean accountExists(String accountNo) {
        return accounts.containsKey(accountNo);
    }

    public void createAccount(Account account) {
        if( accountExists(account.getAccountNo())) {
            throw new DuplicateAccountException();
        }
        accounts.put(account.getAccountNo(), account);
    }

    public void updateAccount(Account account) {
        if( !accountExists(account.getAccountNo())) {
            throw new AccountNotFoundException();
        }
        accounts.put(account.getAccountNo(), account);
    }

    public void removeAccount(Account account) {
        if( !accountExists(account.getAccountNo())) {
            throw new AccountNotFoundException();
        }
        accounts.remove(account.getAccountNo());
    }

    public Account findAccount(String accountNo) {

        Account account = accounts.get(accountNo);
        if( account == null ) {
            throw new AccountNotFoundException();
        }
        return account;
    }

}