package inflearn.jpa.jpql.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public class JpaUtil {
    private static final String PERSISTENCE_UNIT_NAME = "test";

    public static void doTest(Consumer<EntityManager> testMethod) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            testMethod.accept(em);
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}