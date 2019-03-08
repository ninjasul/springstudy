package com.ninjasul.tobyspring31.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SqlMapConfig {

    @Bean
    public Map<String, String> sqlMap() {
        Map<String, String> sqlMap = new HashMap<>();
        sqlMap.put("userAdd", "INSERT INTO USERS( ID, NAME, PASSWORD, LEVEL, LOGINCOUNT, RECOMMENDATIONCOUNT ) VALUES ( ?, ?, ?, ?, ?, ?)" );
        sqlMap.put("userGet", "SELECT * FROM USERS WHERE ID = ?");
        sqlMap.put("userGetAll", "SELECT * FROM USERS ORDER BY ID");
        sqlMap.put("userDeleteAll", "DELETE FROM USERS");
        sqlMap.put("userGetCount", "SELECT COUNT(*) FROM USERS");
        sqlMap.put("userUpdate", "UPDATE USERS SET NAME = ?, PASSWORD = ?, LEVEL = ?, LOGINCOUNT = ?, RECOMMENDATIONCOUNT = ? WHERE ID = ?" );
        return sqlMap;
    }

}