package inflearn.jpa.basic;

import inflearn.jpa.basic.domain.Member;
import inflearn.jpa.basic.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Slf4j
public class _01_HelloJPA {
    public static void main(String[] args) {
        JpaUtil.doTest(_01_HelloJPA::doTest);
    }

    private static void doTest(EntityManager em) {
        Member member = new Member().builder()
                                    .id(1L)
                                    .name("HelloA")
                                    .build();

        em.persist(member);
    }
}