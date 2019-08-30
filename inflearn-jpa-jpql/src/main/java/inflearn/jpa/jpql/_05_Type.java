package inflearn.jpa.jpql;

import inflearn.jpa.jpql.domain.Member;
import inflearn.jpa.jpql.domain.MemberType;
import inflearn.jpa.jpql.domain.Team;
import inflearn.jpa.jpql.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;

import static inflearn.jpa.jpql.domain.MemberType.ADMIN;
import static inflearn.jpa.jpql.domain.MemberType.USER;

@Slf4j
public class _05_Type {
    public static void main(String[] args) {
        JpaUtil.doTest(_05_Type::doTest);
    }

    private static void doTest(EntityManager em) {
        Team team = new Team().builder()
                .name("teamA")
                .build();

        em.persist(team);

        Member member1 = new Member().builder()
                .userName("member1")
                .age(10)
                .team(team)
                .type(ADMIN)
                .build();

        Member member2 = new Member().builder()
                .userName("member2")
                .age(20)
                .team(team)
                .type(USER)
                .build();

        em.persist(member1);
        em.persist(member2);

        em.flush();
        em.clear();
        List<Member> resultList = em.createQuery("select m from Member as m where m.type = :memberType", Member.class)
                .setParameter("memberType", USER)
                .getResultList();

        for (Member curMember : resultList) {
            log.info("curMember: {}", curMember);
        }
    }
}