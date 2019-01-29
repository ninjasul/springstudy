package com.ninjasul.tobyspring31.user.service;

import com.ninjasul.tobyspring31.user.dao.UserDao;
import com.ninjasul.tobyspring31.user.domain.Level;
import com.ninjasul.tobyspring31.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.ninjasul.tobyspring31.user.policy.upgrade.BasicUpgradePolicy.MIN_LOGIN_COUNT_TO_UPGRADE;
import static com.ninjasul.tobyspring31.user.policy.upgrade.SilverUpgradePolicy.MIN_RECOMMENDATION_COUNT_TO_UPGRADE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

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
    public void upgradeLevels() {

        userDao.deleteAll();
        userDao.addList(users);

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
            assertEquals(selectedUser.getLevel(), user.getLevel().nextLevel());
        }
        else {
            assertEquals(selectedUser.getLevel(), user.getLevel());
        }
    }


}