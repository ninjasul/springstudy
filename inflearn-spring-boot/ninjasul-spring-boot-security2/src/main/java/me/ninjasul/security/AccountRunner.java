package me.ninjasul.security;

import me.ninjasul.security.account.Account;
import me.ninjasul.security.account.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(AccountRunner.class);

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account user = accountService.createAccount("ninjasul", "ninjasul");
        logger.info(user.getUsername() + " password: " + user.getPassword());
    }
}