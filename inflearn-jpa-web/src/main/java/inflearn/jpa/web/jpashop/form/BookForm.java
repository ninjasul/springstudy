package inflearn.jpa.web.jpashop.form;

import inflearn.jpa.web.jpashop.domain.item.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookForm {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;

    public BookForm(Long id, String name, int price, int stockQuantity, String author, String isbn) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }

    public static BookForm of(Book item) {
        return new BookForm().builder()
                            .id(item.getId())
                            .name(item.getName())
                            .price(item.getPrice())
                            .stockQuantity(item.getStockQuantity())
                            .author(item.getAuthor())
                            .isbn(item.getIsbn())
                            .build();

    }
}