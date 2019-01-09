package chap16_springtest.bank.dao;

import chap16_springtest.bank.domain.Account;

public interface AccountDao {
    void createAccount(Account account);
    void updateAccount(Account account);
    void removeAccount(Account account);
    Account findAccount(String accountNo);
}