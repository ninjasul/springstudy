package com.ninjasul.tobyspring31.user.dao;

import com.ninjasul.tobyspring31.user.domain.Level;
import com.ninjasul.tobyspring31.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserDaoJdbcTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    @Qualifier("userDaoJdbc")
    private UserDao dao;

    @Autowired
    DataSource dataSource;

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {

        user1 = User.builder()
                .id("gyumee")
                .name("박성철")
                .password("springno1")
                .level(Level.BASIC)
                .loginCount(1)
                .recommendationCount(0)
                .build();

        user2 = User.builder()
                .id("leegw700")
                .name("이길원")
                .password("springno2")
                .level(Level.SILVER)
                .loginCount(55)
                .recommendationCount(10)
                .build();

        user3 = User.builder()
                .id("bumjin")
                .name("박범진")
                .password("springno3")
                .level(Level.GOLD)
                .loginCount(100)
                .recommendationCount(40)
                .build();
    }



    /**
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void update() throws SQLException {

        dao.deleteAll();

        dao.add(user1);

        user1.setName("오민규");
        user1.setPassword("springno6");
        user1.setLevel(Level.GOLD);
        user1.setLoginCount(1000);
        user1.setRecommendationCount(999);
        dao.update(user1);

        User user1Update = dao.get(user1.getId());
        checkSameUser( user1, user1Update );
    }

    @Test
    public void count() throws SQLException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));
        dao.add(user1);
        assertThat(dao.getCount(), is(1));
        dao.add(user2);
        assertThat(dao.getCount(), is(2));
        dao.add(user3);
        assertThat(dao.getCount(), is(3));

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");

    }

    @Test
    public void getAll() {
        dao.deleteAll();

        List<User> users0 = dao.getAll();
        assertThat(users0.size(), is(0));

        dao.add(user1);
        List<User> users1 = dao.getAll();
        assertThat(users1.size(), is(1));
        checkSameUser(user1, users1.get(0));

        dao.add(user2);
        List<User> users2 = dao.getAll();
        assertThat(users2.size(), is(2));
        checkSameUser(user1, users2.get(0));
        checkSameUser(user2, users2.get(1));

        dao.add(user3);
        List<User> users3 = dao.getAll();
        assertThat(users3.size(), is(3));
        checkSameUser(user3, users3.get(0));
        checkSameUser(user1, users3.get(1));
        checkSameUser(user2, users3.get(2));
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
        assertThat(user1.getLevel(), is(user2.getLevel()));
        assertThat(user1.getLoginCount(), is(user2.getLoginCount()));
        assertThat(user1.getRecommendationCount(), is(user2.getRecommendationCount()));
    }

    /*@Test
    public void duplicateKey() {
        dao.deleteAll();

        try {
            dao.add(user1);
            dao.add(user1);
        } catch (DuplicateKeyException e) {
            SQLException sqlEx = (SQLException) e.getRootCause();
            SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
            assertThat(set.translate(null, null, sqlEx), is(DuplicateKeyException.class));

        }
    }*/
}