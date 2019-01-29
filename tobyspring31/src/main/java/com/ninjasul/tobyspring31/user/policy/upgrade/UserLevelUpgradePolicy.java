package com.ninjasul.tobyspring31.user.policy.upgrade;

import com.ninjasul.tobyspring31.user.domain.User;

public interface UserLevelUpgradePolicy {
    boolean canUpgradeLevel(User user);
}