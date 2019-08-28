package inflearn.jpa.basic.domain.inherited.tableperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Entity(name = "BOOK_TABLE_PER_CLASS")
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;
}