package chap16_springtest.bank.service;

public interface AccountService {
    void createAccount(String accountNo);
    void removeAccount(String accountNo);
    void deposit(String accountNo, double amount);
    void withdraw(String accountNo, double amount);
    double getBalance(String accountNo);
}