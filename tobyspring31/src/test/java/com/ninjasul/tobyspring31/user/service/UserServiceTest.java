package com.ninjasul.tobyspring31.user.service;

import com.ninjasul.tobyspring31.user.dao.UserDao;
import com.ninjasul.tobyspring31.user.domain.Level;
import com.ninjasul.tobyspring31.user.domain.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static com.ninjasul.tobyspring31.user.policy.upgrade.BasicUpgradePolicy.MIN_LOGIN_COUNT_TO_UPGRADE;
import static com.ninjasul.tobyspring31.user.policy.upgrade.SilverUpgradePolicy.MIN_RECOMMENDATION_COUNT_TO_UPGRADE;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DataSource dataSource;

    private List<User> users;

    @Before
    public void setUp() throws Exception {
        users = Arrays.asList(
                User.builder()
                    .id("bumjin")
                    .name("박범진")
                    .password("p1")
                    .level(Level.BASIC)
                    .loginCount(MIN_LOGIN_COUNT_TO_UPGRADE-1)
                    .recommendationCount(0)
                    .build(),

                User.builder()
                    .id("joytouch")
                    .name("강명성")
                    .password("p2")
                    .level(Level.BASIC)
                    .loginCount(MIN_LOGIN_COUNT_TO_UPGRADE)
                    .recommendationCount(0)
                    .build(),

                User.builder()
                    .id("erwins")
                    .name("신승한")
                    .password("p3")
                    .level(Level.SILVER)
                    .loginCount(60)
                    .recommendationCount(MIN_RECOMMENDATION_COUNT_TO_UPGRADE-1)
                    .build(),

                User.builder()
                    .id("madnite1")
                    .name("이상호")
                    .password("p4")
                    .level(Level.SILVER)
                    .loginCount(60)
                    .recommendationCount(MIN_RECOMMENDATION_COUNT_TO_UPGRADE)
                    .build(),

                User.builder()
                    .id("green")
                    .name("오민규")
                    .password("p5")
                    .level(Level.GOLD)
                    .loginCount(100)
                    .recommendationCount(Integer.MAX_VALUE)
                    .build()
                );
    }

    @Test
    public void bean() {
        assertThat( userService, notNullValue());
    }


    @Test
    public void upgradeLevels() throws Exception {

        recreateUserList();

        userService.upgradeLevels();

        checkLevelUpgraded( users.get(0), false );
        checkLevelUpgraded( users.get(1), true );
        checkLevelUpgraded( users.get(2), false );
        checkLevelUpgraded( users.get(3), true );
        checkLevelUpgraded( users.get(4), false );
    }

    private void checkLevelUpgraded(User user, boolean upgraded) {

        User selectedUser = userDao.get(user.getId());

        if( upgraded ) {
            assertEquals(user.getLevel().nextLevel(), selectedUser.getLevel());
        }
        else {
            assertEquals(user.getLevel(), selectedUser.getLevel());
        }
    }

    @Test
    public void upgradeAllOrNothing() {

        UserService testUserService = createTestUserService(users.get(3).getId());
        recreateUserList();

        try {
           testUserService.upgradeLevels();
           fail("TestUserServiceException expected");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        checkLevelUpgraded( users.get(1), false );
    }

    private UserService createTestUserService(String id) {
        UserService testUserService = new TestUserService(id);
        testUserService.setUserDao(userDao);
        testUserService.setApplicationContext(applicationContext);
        testUserService.setDataSource(dataSource);
        return testUserService;
    }

    private void recreateUserList() {
        userDao.deleteAll();
        userDao.addList(users);
    }
}