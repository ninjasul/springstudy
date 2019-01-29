package com.ninjasul.tobyspring31.user.policy.upgrade;

import com.ninjasul.tobyspring31.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class BasicUpgradePolicy implements UserLevelUpgradePolicy {

    public static final int MIN_LOGIN_COUNT_TO_UPGRADE = 50;

    @Override
    public boolean canUpgradeLevel(User user) {
        return ( user.getLoginCount() >= MIN_LOGIN_COUNT_TO_UPGRADE);
    }
}