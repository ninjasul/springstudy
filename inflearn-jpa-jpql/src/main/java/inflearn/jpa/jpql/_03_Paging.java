package inflearn.jpa.jpql;

import inflearn.jpa.jpql.domain.Member;
import inflearn.jpa.jpql.dto.MemberDto;
import inflearn.jpa.jpql.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class _03_Paging {
    public static void main(String[] args) {
        JpaUtil.doTest(_03_Paging::doTest);
    }

    private static void doTest(EntityManager em) {
        List<Member> members = getMembers(em, 100);

        em.flush();
        em.clear();

        List<Member> resultList = em.createQuery("select m from Member as m order by m.age desc", Member.class)
                .setFirstResult(10)
                .setMaxResults(20)
                .getResultList();

        for (Member curMember : resultList) {
            log.info("curMember: {}", curMember);
        }
    }

    private static List<Member> getMembers(EntityManager em, int count) {
        List<Member> members = new ArrayList<>();

        for (int i = 0; i < count; ++i) {
            Member newMember = new Member().builder()
                    .userName(String.format("member%03d", i+1))
                    .age(i+1)
                    .build();

            members.add(newMember);
            em.persist(newMember);
        }


        return members;
    }
}