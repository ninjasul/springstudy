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
public class _12_FetchJoinBasic {
    public static void main(String[] args) {
        JpaUtil.doTest(_12_FetchJoinBasic::doTest);
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

        String query = "select m From Member m";

        // 이 쿼리를 실행 시키면 1차적으로 Member 테이블을 조회함.
        List<Member> resultList = em.createQuery(query, Member.class)
                .getResultList();

        // 루프를 돌며 team 이름을 가져오려고 할 때 팀 별로 Team 테이블을 조회하게 됨. 즉,
        // member1: 팀A(SQL)
        // member2: 팀A(1차 캐시)
        // member3: 팀B(SQL)
        // 와 같이 추가 쿼리가 2개 더 나감.
        for (Member curMember : resultList) {
            log.info("memberName: {}, teamName: {}", curMember.getUserName(), curMember.getTeam().getName());
        }

        em.flush();
        em.clear();

        // 페치 조인으로 가져오면 추가 쿼리가 나가지 않음.
        query = "select m From Member m join fetch m.team";

        resultList = em.createQuery(query, Member.class)
                .getResultList();

        for (Member curMember : resultList) {
            log.info("memberName: {}, teamName: {}", curMember.getUserName(), curMember.getTeam().getName());
        }
    }
}