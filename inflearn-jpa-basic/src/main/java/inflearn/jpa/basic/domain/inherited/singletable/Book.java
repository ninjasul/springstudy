package inflearn.jpa.basic.domain.inherited.singletable;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Entity(name = "BOOK_SINGLE_TABLE")
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;
}