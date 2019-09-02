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
public class _15_BulkOperation {
    public static void main(String[] args) {
        JpaUtil.doTest(_15_BulkOperation::doTest);
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

        int resultCount = em.createQuery("update Member m set m.age = 20")
                .executeUpdate();

        log.info("resultCount: {}", resultCount);


        //em.flush();
        //em.clear();
        // 벌크 연산은 영속성 컨텍스트를 거치지 않기 때문에 clear 를 하지 않고 조회하면 업데이트 되지 않은 데이터를 조회할 수 있음.
        Member foundMember = em.find(Member.class, member1.getId());

        log.info("member1's age: {}", member1.getAge());
        log.info("foundMember's age: {}", foundMember.getAge());

        List<Member> resultList = em.createQuery("select m from Member m", Member.class)
                                    .getResultList();

        for (Member curMember : resultList) {
            log.info("curMember: {}", curMember);
        }
    }
}