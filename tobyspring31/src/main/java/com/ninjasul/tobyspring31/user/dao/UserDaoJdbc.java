package com.ninjasul.tobyspring31.user.dao;

import com.ninjasul.tobyspring31.user.domain.Level;
import com.ninjasul.tobyspring31.user.domain.User;
import com.ninjasul.tobyspring31.user.sqlservice.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserDaoJdbc implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SqlService sqlService;

    private RowMapper<User> userMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setLevel(Level.valueOf(rs.getInt("level")));
        user.setLoginCount(rs.getInt("logincount"));
        user.setRecommendationCount(rs.getInt("recommendationcount"));
        return user;
    };

    public UserDaoJdbc() {
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update(sqlService.getSql("userAdd"),
                                user.getId(),
                                user.getName(),
                                user.getPassword(),
                                user.getLevel().intValue(),
                                user.getLoginCount(),
                                user.getRecommendationCount()
        );

    }

    @Override
    public void addList(List<User> users) {

        for( User user : users ) {
            jdbcTemplate.update(sqlService.getSql("userAdd"),
                    user.getId(),
                    user.getName(),
                    user.getPassword(),
                    user.getLevel().intValue(),
                    user.getLoginCount(),
                    user.getRecommendationCount()
            );
        }
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(sqlService.getSql("userUpdate"),
                user.getName(),
                user.getPassword(),
                user.getLevel().intValue(),
                user.getLoginCount(),
                user.getRecommendationCount(),
                user.getId()
        );

    }

    @Override
    public User get(String id) {
        return jdbcTemplate.queryForObject(sqlService.getSql("userGet"),
                                            new Object[] { id },
                                            userMapper
                );
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(sqlService.getSql("userDeleteAll"));
    }

    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject(sqlService.getSql("userGetCount"), Integer.class);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(sqlService.getSql("userGetAll"), userMapper);
    }
}