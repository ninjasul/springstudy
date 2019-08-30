package inflearn.jpa.jpql;

import inflearn.jpa.jpql.domain.Member;
import inflearn.jpa.jpql.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;

import static inflearn.jpa.jpql.domain.MemberType.ADMIN;
import static inflearn.jpa.jpql.domain.MemberType.USER;

@Slf4j
public class _07_Coalesce {
    public static void main(String[] args) {
        JpaUtil.doTest(_07_Coalesce::doTest);
    }

    private static void doTest(EntityManager em) {
        Member member1 = new Member().builder()
                .userName("member1")
                .age(10)
                .type(ADMIN)
                .build();

        Member member2 = new Member().builder()
                .userName(null)
                .age(30)
                .type(USER)
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

        String query = "select coalesce(m.userName, '무명씨') from Member as m";

        List<String> resultList = em.createQuery(query, String.class)
                .getResultList();

        for (String curName : resultList) {
            log.info("curName: {}", curName);
        }
    }
}