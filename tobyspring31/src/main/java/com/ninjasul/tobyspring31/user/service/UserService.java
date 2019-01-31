package com.ninjasul.tobyspring31.user.service;

import com.ninjasul.tobyspring31.user.domain.User;

public interface UserService {
    void add(User user);
    void upgradeLevels() throws Exception;
}