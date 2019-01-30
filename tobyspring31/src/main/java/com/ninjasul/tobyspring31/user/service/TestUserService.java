package com.ninjasul.tobyspring31.user.service;

import com.ninjasul.tobyspring31.user.domain.User;

public class TestUserService extends UserService {

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