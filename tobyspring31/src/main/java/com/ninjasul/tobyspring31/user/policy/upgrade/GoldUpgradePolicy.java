package com.ninjasul.tobyspring31.user.policy.upgrade;

import com.ninjasul.tobyspring31.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class GoldUpgradePolicy implements UserLevelUpgradePolicy {
    @Override
    public boolean canUpgradeLevel(User user) {
        return false;
    }
}