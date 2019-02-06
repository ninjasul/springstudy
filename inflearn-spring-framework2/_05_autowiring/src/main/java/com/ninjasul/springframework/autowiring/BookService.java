package com.ninjasul.springframework.autowiring;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.Date;

@Service
public class BookService {

    @Autowired
    @Qualifier("ninjasulBookRepository")
    @Setter
    BookRepository bookRepository;

    public Book save(Book book) {
        book.setCreated(new Date());
        book.setBookStatus(BookStatus.DRAFT);
        return bookRepository.save(book);
    }
}
