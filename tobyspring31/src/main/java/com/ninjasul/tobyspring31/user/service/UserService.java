package com.ninjasul.tobyspring31.user.service;

import com.ninjasul.tobyspring31.user.dao.UserDao;
import com.ninjasul.tobyspring31.user.domain.User;
import com.ninjasul.tobyspring31.user.policy.upgrade.UserLevelUpgradePolicy;
import lombok.Setter;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
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
    protected DataSource dataSource;

    public void upgradeLevels() throws Exception {

        Connection connection = beginTransaction(dataSource);

        try {
            List<User> users = userDao.getAll();

            for (User user : users) {
                if (canUpgradeLevel(user)) {
                    upgradeLevel(user);
                }
            }

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            endTransaction(connection);
        }
    }

    private Connection beginTransaction(DataSource dataSource) throws Exception {
        TransactionSynchronizationManager.initSynchronization();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        connection.setAutoCommit(false);
        return connection;
    }

    private void endTransaction(Connection connection) {
        DataSourceUtils.releaseConnection(connection, dataSource);
        TransactionSynchronizationManager.unbindResource(dataSource);
        TransactionSynchronizationManager.clearSynchronization();
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