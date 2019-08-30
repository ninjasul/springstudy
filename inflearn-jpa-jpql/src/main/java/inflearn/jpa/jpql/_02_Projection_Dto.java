package inflearn.jpa.jpql;

import inflearn.jpa.jpql.domain.Member;
import inflearn.jpa.jpql.dto.MemberDto;
import inflearn.jpa.jpql.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
public class _02_Projection_Dto {
    public static void main(String[] args) {
        JpaUtil.doTest(_02_Projection_Dto::doTest);
    }

    private static void doTest(EntityManager em) {
        Member member = new Member().builder()
                .userName("member1")
                .age(10)
                .build();

        em.persist(member);

        em.flush();
        em.clear();

        List<MemberDto> resultList = em.createQuery("select new inflearn.jpa.jpql.dto.MemberDto(m.userName, m.age) from Member as m", MemberDto.class)
                .getResultList();

        for (MemberDto curMember : resultList) {
            log.info("curMember: {}", curMember.getUserName());
        }

        em.persist(member);
    }
}