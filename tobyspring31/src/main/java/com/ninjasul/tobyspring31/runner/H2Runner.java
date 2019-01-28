package com.ninjasul.tobyspring31.runner;

import jdk.internal.instrumentation.Logger;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
@Log4j2
public class H2Runner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        /*try( Connection connection = dataSource.getConnection() ) {
            log.info("url: {}", connection.getMetaData().getURL());
            log.info("username: {}", connection.getMetaData().getUserName());

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE USERS  (\n" +
                    "    id VARCHAR(10) NOT NULL PRIMARY KEY,\n" +
                    "    name VARCHAR(20) NOT NULL,\n" +
                    "    password VARCHAR(10) NOT NULL,\n" +
                    "    level INTEGER,\n" +
                    "    logincount INTEGER,\n" +
                    "    recommendationcount INTEGER\n" +
                    "  );";

            statement.executeUpdate(sql);
        }
*/
        String sql = "CREATE TABLE USERS  (\n" +
                "    id VARCHAR(10) NOT NULL PRIMARY KEY,\n" +
                "    name VARCHAR(20) NOT NULL,\n" +
                "    password VARCHAR(10) NOT NULL,\n" +
                "    level INTEGER,\n" +
                "    logincount INTEGER,\n" +
                "    recommendationcount INTEGER\n" +
                "  );";

        jdbcTemplate.execute(sql);
    }
}
