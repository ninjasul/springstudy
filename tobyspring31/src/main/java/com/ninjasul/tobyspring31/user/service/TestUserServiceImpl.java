package com.ninjasul.tobyspring31.user.service;

import com.ninjasul.tobyspring31.user.domain.User;
import com.ninjasul.tobyspring31.user.service.UserServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TestUserServiceImpl extends UserServiceImpl {

    private String id = "madnite1";

    @Override
    public void upgradeLevel(User user) {
        if( user.getId().equals(id)) {
            throw new TestUserServiceException();
        }

        super.upgradeLevel(user);
    }
}