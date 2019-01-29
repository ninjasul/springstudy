package com.ninjasul.tobyspring31.user.dao;

import com.ninjasul.tobyspring31.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {
    void add(User user);
    void addList(List<User> users);
    void update(User user);
    User get(String id);
    void deleteAll();
    int getCount();
    List<User> getAll();
}