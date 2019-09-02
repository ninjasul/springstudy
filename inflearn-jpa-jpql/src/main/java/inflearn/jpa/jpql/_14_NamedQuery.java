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
public class _14_NamedQuery {
    public static void main(String[] args) {
        JpaUtil.doTest(_14_NamedQuery::doTest);
    }

    private static void doTest(EntityManager em) {
        Team teamA = new Team().builder()
                                .name("TeamA")
                                .build();

        Team teamB = new Team().builder()
                .name("TeamB")
                .build();


        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member().builder()
                .userName("member1")
                .age(10)
                .type(ADMIN)
                .team(teamA)
                .build();

        Member member2 = new Member().builder()
                .userName("member2")
                .age(30)
                .type(USER)
                .team(teamA)
                .build();

        Member member3 = new Member().builder()
                .userName("member3")
                .age(70)
                .type(USER)
                .team(teamB)
                .build();

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        em.flush();
        em.clear();

        List<Member> resultList = em.createNamedQuery("Member.findByUserName", Member.class)
                .setParameter("userName", "member1")
                .getResultList();

        for (Member curMember : resultList) {
                log.info("curMemberName: {}", curMember);
        }
    }
}