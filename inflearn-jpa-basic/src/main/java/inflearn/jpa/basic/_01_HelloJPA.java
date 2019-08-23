package inflearn.jpa.basic;

import inflearn.jpa.basic.domain.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Slf4j
public class _01_HelloJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        doTest(em);

        tx.commit();
        em.close();

        emf.close();
    }

    private static void doTest(EntityManager em) {
        Member member = new Member().builder()
                                    .id(1L)
                                    .name("HelloA")
                                    .build();

        em.persist(member);
    }
}