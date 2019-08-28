package inflearn.jpa.basic.domain.inherited.joined;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Entity(name = "BOOK_JOINED")
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;
}