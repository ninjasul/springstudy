package inflearn.jpa.basic;

import inflearn.jpa.basic.domain.Member;
import inflearn.jpa.basic.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Slf4j
public class _06_Proxy {
    public static void main(String[] args) {
        JpaUtil.doTest(_06_Proxy::doTest);
    }

    private static void doTest(EntityManager em) {
        Member member = new Member();
        member.setName("hello");

        em.persist(member);

        em.flush();
        em.clear();

        //Member foundMember = em.find(Member.class, member.getId());
        Member foundMember = em.getReference(Member.class, member.getId());
    }
}