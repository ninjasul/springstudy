package inflearn.jpa.jpql;

import inflearn.jpa.jpql.domain.Member;
import inflearn.jpa.jpql.domain.Team;
import inflearn.jpa.jpql.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.Collection;

import static inflearn.jpa.jpql.domain.MemberType.ADMIN;
import static inflearn.jpa.jpql.domain.MemberType.USER;

@Slf4j
public class _11_CollectionRelatedPath {
    public static void main(String[] args) {
        JpaUtil.doTest(_11_CollectionRelatedPath::doTest);
    }

    private static void doTest(EntityManager em) {
        Team team = new Team().builder()
                                .name("TeamA")
                                .build();

        em.persist(team);

        Member member1 = new Member().builder()
                .userName("member1")
                .age(10)
                .type(ADMIN)
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
                .team(team)
                .build();

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        em.flush();
        em.clear();

        // 아래와 같이 엔티티의 컬렉션 값을 조회하는 경우에는 묵시적 inner join 이 발생함.
        // 그리고 컬렉션 값 연관 경로는 1차 탐색만 가능함.
        // 즉, t.members.XXX 로 2차 탐색이 불가능 하다는 말임.
        // 만약 members 의 필드를 조회 하려면 묵시적 조인이 아닌 명시적 조인을 사용해야 함.
        String query = "select t.members from Team as t";

        Collection resultList = em.createQuery(query, Collection.class)
                .getResultList();

        for (Object curObj : resultList) {
            log.info("curObj: {}", curObj);
        }

        // 컬렉션 값 연관 경로이기 때문에 size 값 정도는 얻을 수 있음.
        query = "select t.members.size from Team as t";

        Integer result = em.createQuery(query, Integer.class)
                .getSingleResult();

        log.info("result: {}", result);
    }
}