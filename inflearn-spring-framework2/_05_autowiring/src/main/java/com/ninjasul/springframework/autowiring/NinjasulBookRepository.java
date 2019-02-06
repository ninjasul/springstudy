package com.ninjasul.springframework.autowiring;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
@Log4j2
public class NinjasulBookRepository implements BookRepository {

    @Override
    public Book save(Book book) {
        return null;
    }

}