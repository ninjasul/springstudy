package com.ninjasul.tobyspring31.user.service;

import com.ninjasul.tobyspring31.user.dao.UserDao;
import com.ninjasul.tobyspring31.user.domain.Level;
import com.ninjasul.tobyspring31.user.domain.User;
import com.ninjasul.tobyspring31.user.service.helpers.MockMailSender;
import com.ninjasul.tobyspring31.user.service.helpers.TestUserService;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

import static com.ninjasul.tobyspring31.user.policy.upgrade.BasicUpgradePolicy.MIN_LOGIN_COUNT_TO_UPGRADE;
import static com.ninjasul.tobyspring31.user.policy.upgrade.SilverUpgradePolicy.MIN_RECOMMENDATION_COUNT_TO_UPGRADE;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @MockBean
    private MailSender mailSender;

    private List<User> users;

    @Before
    public void setUp() throws Exception {
        users = Arrays.asList(
                User.builder()
                        .id("bumjin")
                        .name("박범진")
                        .password("p1")
                        .level(Level.BASIC)
                        .loginCount(MIN_LOGIN_COUNT_TO_UPGRADE - 1)
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
                        .recommendationCount(MIN_RECOMMENDATION_COUNT_TO_UPGRADE - 1)
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
        assertThat(userService, notNullValue());
    }


    @Test
    public void add() {
        userDao.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelSelected = userDao.get(userWithLevel.getId());
        User userWithoutLevelSelected = userDao.get(userWithoutLevel.getId());

        assertEquals(userWithLevel.getLevel(), userWithLevelSelected.getLevel());
        assertEquals(Level.BASIC, userWithoutLevelSelected.getLevel());
    }

    @Test
    public void upgradeLevels() throws Exception {

        recreateUserList();

        MockMailSender mockMailSender = getMockMailSender();

        userService.upgradeLevels();

        checkLevelUpgraded(users.get(0), false);
        checkLevelUpgraded(users.get(1), true);
        checkLevelUpgraded(users.get(2), false);
        checkLevelUpgraded(users.get(3), true);
        checkLevelUpgraded(users.get(4), false);

        checkMailReceivers(mockMailSender);
    }

    private MockMailSender getMockMailSender() {
        MockMailSender mockMailSender = new MockMailSender();
        userService.setMailSender(mockMailSender);
        return mockMailSender;
    }

    private void checkLevelUpgraded(User user, boolean upgraded) {

        User selectedUser = userDao.get(user.getId());

        if (upgraded) {
            assertEquals(user.getLevel().nextLevel(), selectedUser.getLevel());
        } else {
            assertEquals(user.getLevel(), selectedUser.getLevel());
        }
    }

    private void checkMailReceivers(MockMailSender mockMailSender) {
        List<String> request = mockMailSender.getRequests();
        assertEquals(request.size(), 2);
        assertEquals(request.get(0), users.get(1).getEmail());
        assertEquals(request.get(1), users.get(3).getEmail());
    }

    @Test
    public void upgradeLevelsWithMockBean() throws Exception {

        recreateUserList();

        userService.setMailSender(mailSender);
        userService.upgradeLevels();

        checkLevelUpgraded(users.get(0), false);
        checkLevelUpgraded(users.get(1), true);
        checkLevelUpgraded(users.get(2), false);
        checkLevelUpgraded(users.get(3), true);
        checkLevelUpgraded(users.get(4), false);

        checkMailSenderWithMockBean();
    }

    private void checkMailSenderWithMockBean() {
        ArgumentCaptor<SimpleMailMessage> mailMessageArgumentCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);

        verify(mailSender, times(2)).send(mailMessageArgumentCaptor.capture());

        List<SimpleMailMessage> mailMessages = mailMessageArgumentCaptor.getAllValues();
        assertEquals(users.get(1).getEmail(), mailMessages.get(0).getTo()[0]);
        assertEquals(users.get(3).getEmail(), mailMessages.get(1).getTo()[0]);
    }

    @Test
    public void upgradeAllOrNothing() {

        UserServiceImpl testUserService = getTestUserService(users.get(3).getId());
        UserServiceTx userServiceTx = getUserServiceTx();

        recreateUserList();

        try {
            userServiceTx.upgradeLevels();
            fail("TestUserServiceException expected");
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkLevelUpgraded(users.get(1), false);
    }

    private UserServiceTx getUserServiceTx() {
        UserServiceTx userServiceTx = new UserServiceTx();
        userServiceTx.setTransactionManager(transactionManager);
        return userServiceTx;
    }

    @Test
    public void upgradeAllOrNothingWithTransactionHandler() {

        UserServiceImpl testUserService = getTestUserService(users.get(3).getId());
        TransactionHandler transactionHandler = getTransactionHandler(testUserService);
        UserService txUserService = getProxiedUserService(transactionHandler);

        recreateUserList();

        try {
            txUserService.upgradeLevels();
            fail("TestUserServiceException expected");
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkLevelUpgraded(users.get(1), false);
    }

    private TransactionHandler getTransactionHandler(UserServiceImpl testUserService) {
        TransactionHandler transactionHandler = new TransactionHandler();
        transactionHandler.setTarget(testUserService);
        transactionHandler.setTransactionManager(transactionManager);
        transactionHandler.setPattern("upgradeLevels");
        return transactionHandler;
    }

    private UserService getProxiedUserService(TransactionHandler transactionHandler) {
        return (UserService) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{UserService.class},
                transactionHandler
        );
    }

    private UserServiceImpl getTestUserService(String id) {
        UserServiceImpl testUserService = new TestUserService(id);
        testUserService.setUserDao(userDao);
        testUserService.setApplicationContext(applicationContext);
        testUserService.setMailSender(mailSender);
        return testUserService;
    }

    private void recreateUserList() {
        userDao.deleteAll();
        userDao.addList(users);
    }

    @Test
    public void defaultTransactionManagerType() {
        assertThat(transactionManager, IsInstanceOf.instanceOf(DataSourceTransactionManager.class));
    }
}