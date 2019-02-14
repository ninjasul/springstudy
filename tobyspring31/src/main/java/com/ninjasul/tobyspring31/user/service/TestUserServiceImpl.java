package com.ninjasul.tobyspring31.user.service;

import com.ninjasul.tobyspring31.user.domain.User;
import com.ninjasul.tobyspring31.user.service.UserServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("testUserService")
public class TestUserServiceImpl extends UserServiceImpl {

    private String id = "madnite1";

    @Override
    public List<User> getAll() {
        for( User user : super.getAll() ) {
            update(user);
        }

        return null;
    }

    @Override
    public void upgradeLevel(User user) {
        if( user.getId().equals(id)) {
            throw new TestUserServiceException();
        }

        super.upgradeLevel(user);
    }
}