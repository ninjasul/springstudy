package inflearn.jpa.shop;

import inflearn.jpa.shop.domain.Book;
import inflearn.jpa.shop.domain.Movie;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Slf4j
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            doTest(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void doTest(EntityManager em) {
        Book book = new Book();
        book.setName("JPA");
        book.setAuthor("Kim");

        Movie movie = new Movie();
        movie.setName("Avengers");
        movie.setDirector("Lusso Brothers");

        em.persist(book);
        em.persist(movie);
    }
}