package com.ninjasul.springframework.applicationcontext.javaconfigbycomponentscan;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookService {

    @Autowired
    @Setter
    BookRepository bookRepository;

    public Book save(Book book) {
        book.setCreated(new Date());
        book.setBookStatus(BookStatus.DRAFT);
        return bookRepository.save(book);
    }
}
