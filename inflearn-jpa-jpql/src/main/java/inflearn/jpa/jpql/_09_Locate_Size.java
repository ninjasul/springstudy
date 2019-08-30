package inflearn.jpa.jpql;

import inflearn.jpa.jpql.domain.Member;
import inflearn.jpa.jpql.domain.Team;
import inflearn.jpa.jpql.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;

import static inflearn.jpa.jpql.domain.MemberType.ADMIN;
import static inflearn.jpa.jpql.domain.MemberType.USER;

@Slf4j
public class _09_Locate_Size {
    public static void main(String[] args) {
        JpaUtil.doTest(_09_Locate_Size::doTest);
    }

    private static void doTest(EntityManager em) {
        Team team = new Team().builder()
                .name("teamA")
                .build();

        em.persist(team);

        Member member1 = new Member().builder()
                .userName("member1")
                .age(10)
                .type(ADMIN)
                .team(team)
                .build();

        Member member2 = new Member().builder()
                .userName("member2")
                .age(30)
                .type(USER)
                .team(team)
                .build();

        Member member3 = new Member().builder()
                .userName("member3")
                .age(70)
                .type(USER)
                .build();

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        em.flush();
        em.clear();

        String query = "select locate('de', 'abcdefg') from Team as t";

        List<Integer> resultList = em.createQuery(query, Integer.class)
                .getResultList();

        for (Integer curVal : resultList) {
            log.info("locate: {}", curVal);
        }

        query = "select size(t.members) from Team as t";

        resultList = em.createQuery(query, Integer.class)
                .getResultList();

        for (Integer curVal : resultList) {
            log.info("size: {}", curVal);
        }
    }
}