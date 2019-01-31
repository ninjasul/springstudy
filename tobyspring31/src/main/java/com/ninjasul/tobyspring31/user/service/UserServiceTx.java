package com.ninjasul.tobyspring31.user.service;

import com.ninjasul.tobyspring31.user.domain.User;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class UserServiceTx implements UserService {

    @Autowired
    @Setter
    private UserService userService;

    @Autowired
    @Setter
    protected PlatformTransactionManager transactionManager;

    @Override
    public void add(User user) {
        userService.add(user);
    }

    @Override
    public void upgradeLevels() throws Exception {
        TransactionStatus status = beginTransaction();

        try {
            userService.upgradeLevels();
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    private TransactionStatus beginTransaction() throws Exception {
        return transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

}