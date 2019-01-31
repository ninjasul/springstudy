package com.ninjasul.tobyspring31.user.service.helpers;

import com.ninjasul.tobyspring31.user.domain.User;
import com.ninjasul.tobyspring31.user.service.UserServiceImpl;

public class TestUserService extends UserServiceImpl {

    private String id;

    public TestUserService(String id) {
        this.id = id;
    }

    @Override
    public void upgradeLevel(User user) {
        if( user.getId().equals(id)) {
            throw new TestUserServiceException();
        }

        super.upgradeLevel(user);
    }
}