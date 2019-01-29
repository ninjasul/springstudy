package com.ninjasul.tobyspring31.user.service;

import com.ninjasul.tobyspring31.user.dao.UserDao;
import com.ninjasul.tobyspring31.user.domain.User;
import com.ninjasul.tobyspring31.user.policy.upgrade.UserLevelUpgradePolicy;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationContext applicationContext;

    public void upgradeLevels() {
        List<User> users = userDao.getAll();

        for( User user : users ) {
            if( canUpgradeLevel(user) ) {
                user.setLevel(user.getLevel().nextLevel());
                userDao.update(user);
            }
        }
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
}