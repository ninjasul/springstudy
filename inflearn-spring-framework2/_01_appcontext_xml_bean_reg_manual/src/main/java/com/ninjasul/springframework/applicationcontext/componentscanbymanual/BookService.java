package com.ninjasul.springframework.applicationcontext.componentscanbymanual;

import lombok.Setter;

import java.util.Date;

public class BookService {

    @Setter
    BookRepository bookRepository;

    public Book save(Book book) {
        book.setCreated(new Date());
        book.setBookStatus(BookStatus.DRAFT);
        return bookRepository.save(book);
    }
}
