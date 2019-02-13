package com.ninjasul.springframework.applicationcontext.javaconfigbymanual;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public BookService bookService() {
        BookService bookService = new BookService();
        bookService.setBookRepository(bookRepository());
        return bookService;
    }

    @Bean
    public BookRepository bookRepository() {
        return new BookRepository();
    }
}
