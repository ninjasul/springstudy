package inflearn.jpa.basic;

import inflearn.jpa.basic.domain.inherited.singletable.Movie;
import inflearn.jpa.basic.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;

@Slf4j
public class _03_Inherited_SingleTable {
    public static void main(String[] args) {
        JpaUtil.doTest(_03_Inherited_SingleTable::doTest);
    }

    private static void doTest(EntityManager em) {
        Movie movie = new Movie("바람과 함께 사라지다.", 10000, "aaaa", "bbbb");

        em.persist(movie);

        em.flush();
        em.clear();

        Movie foundMovie = em.find(Movie.class, movie.getId());
        log.info("foundMovie: {}", foundMovie);
    }
}