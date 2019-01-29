package com.ninjasul.tobyspring31.user.dao;

import com.ninjasul.tobyspring31.user.domain.Level;
import com.ninjasul.tobyspring31.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDaoJdbc implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @Override
    public void add(User user) {
        jdbcTemplate.update("INSERT INTO USERS( ID, NAME, PASSWORD, LEVEL, LOGINCOUNT, RECOMMENDATIONCOUNT ) " +
                        "           VALUES ( ?, ?, ?, ?, ?, ?)",
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
            jdbcTemplate.update("INSERT INTO USERS( ID, NAME, PASSWORD, LEVEL, LOGINCOUNT, RECOMMENDATIONCOUNT ) " +
                            "           VALUES ( ?, ?, ?, ?, ?, ?)",
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
        jdbcTemplate.update("UPDATE USERS SET NAME = ?, PASSWORD = ?, LEVEL = ?, LOGINCOUNT = ?, RECOMMENDATIONCOUNT = ? WHERE ID = ?",
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
        return jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE ID = ?",
                                            new Object[] { id },
                                            userMapper
                );
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM USERS");
    }

    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USERS", Integer.class);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM USERS ORDER BY ID", userMapper);
    }
}