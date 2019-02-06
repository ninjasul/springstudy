package com.ninjasul.springframework.applicationcontext;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Builder
public class Book {
    private Date created;
    private BookStatus bookStatus;
}
