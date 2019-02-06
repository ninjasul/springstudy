package com.ninjasul.springframework.autowiring;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class BookRepositoryTest {

    @Autowired
    ApplicationContext context;

    /*************************************************************************************
     * 1. 구현 클래스 상단에 @Primary 사용
     *************************************************************************************/
    /*************************************************************************************
     * 2. @Autowired & @Qualifier 사용
     *************************************************************************************/
     // @Autowired
     // @Qualifier("ninjasulBookRepository")

    /*************************************************************************************
     * 3. @Resource & name attribute 사용
     *************************************************************************************/
    // @Resource(name = "dyBookRepository")

    /*************************************************************************************
     * 4. @Autowired & 변수명을 해당 빈 이름으로 변경
     *************************************************************************************/
    // @Autowired
    // BookRepository dyBookRepository;

    /*************************************************************************************
     * 5. @Autowired & 컬렉션 객체로 모든 빈을 주입 받음
     *************************************************************************************/
    @Autowired
    Set<BookRepository> bookRepositories;

   /* @Test
    public void bean() {
        assertNotNull(dyBookRepository);
        log.info("bookRepository class: {}", dyBookRepository.getClass());
    }*/

    @Test
    public void beans() {
        assertNotNull(bookRepositories);
        assertFalse(bookRepositories.isEmpty());
        bookRepositories.forEach( r -> log.info("bookRepository class: {}", r.getClass()) );
    }

}