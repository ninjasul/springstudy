package com.ninjasul.tobyspring31.user.policy.upgrade;

import com.ninjasul.tobyspring31.user.domain.Level;
import com.ninjasul.tobyspring31.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class SilverUpgradePolicy implements UserLevelUpgradePolicy {

    public static final int MIN_RECOMMENDATION_COUNT_TO_UPGRADE = 30;

    @Override
    public boolean canUpgradeLevel(User user) {
        return user.getRecommendationCount() >= MIN_RECOMMENDATION_COUNT_TO_UPGRADE;
    }
}