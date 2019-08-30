package inflearn.jpa.jpql;

import inflearn.jpa.jpql.domain.Member;
import inflearn.jpa.jpql.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
public class _01_HelloJPQL {
    public static void main(String[] args) {
        JpaUtil.doTest(_01_HelloJPQL::doTest);
    }

    private static void doTest(EntityManager em) {
        Member member = new Member().builder()
                .userName("member1")
                .age(10)
                .build();

        List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                .getResultList();

        for (Member curMember : resultList) {
            log.info("curMember: {}", curMember);
        }

        em.persist(member);
    }
}