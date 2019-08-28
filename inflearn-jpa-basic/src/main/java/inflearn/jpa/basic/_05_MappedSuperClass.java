package inflearn.jpa.basic;

import inflearn.jpa.basic.domain.inherited.tableperclass.Movie;
import inflearn.jpa.basic.domain.mappedsuperclass.Member;
import inflearn.jpa.basic.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Slf4j
public class _05_MappedSuperClass {
    public static void main(String[] args) {
        JpaUtil.doTest(_05_MappedSuperClass::doTest);
    }

    private static void doTest(EntityManager em) {
        Member member = new Member();
        member.setCreatedBy("Kim");
        member.setCreatedDate(LocalDateTime.now());

        em.persist(member);

        em.flush();
        em.clear();
    }
}