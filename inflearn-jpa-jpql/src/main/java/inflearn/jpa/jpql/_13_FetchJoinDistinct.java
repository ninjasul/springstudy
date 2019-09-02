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
public class _13_FetchJoinDistinct {
    public static void main(String[] args) {
        JpaUtil.doTest(_13_FetchJoinDistinct::doTest);
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

        // 1대다 페치 조인 시 조회 데이터 개수가 늘어날 수 있으므로 유의 해야 함.
        // distinct 를 걸어서 중복 데이터를 줄일 수 있음.
        String query = "select distinct t From Team t join fetch t.members";

        List<Team> resultList = em.createQuery(query, Team.class)
                .getResultList();

        for (Team curTeam : resultList) {
            log.info("curTeam: {}", curTeam.getName());
            for (Member curMember : curTeam.getMembers()) {
                log.info("curMemberName: {}", curMember.getUserName());
            }
        }
    }
}