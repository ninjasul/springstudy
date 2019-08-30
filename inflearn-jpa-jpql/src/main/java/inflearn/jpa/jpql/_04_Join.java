package inflearn.jpa.jpql;

import inflearn.jpa.jpql.domain.Member;
import inflearn.jpa.jpql.domain.Team;
import inflearn.jpa.jpql.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
public class _04_Join {
    public static void main(String[] args) {
        JpaUtil.doTest(_04_Join::doTest);
    }

    private static void doTest(EntityManager em) {
        Team team = new Team().builder()
                        .name("teamA")
                        .build();

        em.persist(team);

        Member member = new Member().builder()
                .userName("teamA")
                .age(10)
                .team(team)
                .build();

        em.persist(member);

        em.flush();
        em.clear();

        // inner 조인
        //List<Member> resultList = em.createQuery("select m from Member as m join m.team t", Member.class)

        // outer 조인
        //List<Member> resultList = em.createQuery("select m from Member as m left join m.team t", Member.class)

        // On 절을 사용한 조인
        //List<Member> resultList = em.createQuery("select m from Member as m left join m.team t on t.name = 'teamA'", Member.class)

        // 연관 관계 없는 On 조인
        List<Member> resultList = em.createQuery("select m from Member as m left join Team as t on m.userName = t.name", Member.class)
                .getResultList();

        for (Member curMember : resultList) {
            log.info("curMember: {}", curMember);
        }
    }
}