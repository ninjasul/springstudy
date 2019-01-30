package com.ninjasul.tobyspring31.user.service;

import com.ninjasul.tobyspring31.user.dao.UserDao;
import com.ninjasul.tobyspring31.user.domain.User;
import com.ninjasul.tobyspring31.user.policy.upgrade.UserLevelUpgradePolicy;
import lombok.Setter;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Service
public class UserService {

    @Autowired
    @Setter
    protected UserDao userDao;

    @Autowired
    @Setter
    protected ApplicationContext applicationContext;

    @Autowired
    @Setter
    protected PlatformTransactionManager transactionManager;

    public void upgradeLevels() throws Exception {

        TransactionStatus status = beginTransaction();

        try {
            List<User> users = userDao.getAll();

            for (User user : users) {
                if (canUpgradeLevel(user)) {
                    upgradeLevel(user);
                }
            }

            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    private TransactionStatus beginTransaction() throws Exception {
        return transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    boolean canUpgradeLevel(User user) {

        boolean canUpgradeLevel = false;

        UserLevelUpgradePolicy policy = applicationContext.getBean(
                CaseUtils.toCamelCase(user.getLevel().name(), false) + "UpgradePolicy",
                UserLevelUpgradePolicy.class
        );

        if( policy != null ) {
            canUpgradeLevel = policy.canUpgradeLevel(user);
        }

        return canUpgradeLevel;
    }

    protected void upgradeLevel(User user) {
        user.setLevel(user.getLevel().nextLevel());
        userDao.update(user);
    }
}