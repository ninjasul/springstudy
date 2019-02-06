package com.ninjasul.springframework.autowiring;

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
