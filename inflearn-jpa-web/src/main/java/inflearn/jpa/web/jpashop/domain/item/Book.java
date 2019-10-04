package inflearn.jpa.web.jpashop.domain.item;

import inflearn.jpa.web.jpashop.form.BookForm;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Entity
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;

    protected Book(Long id, String name, int price, int stockQuantity, String author, String isbn) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public static class BookBuilder {
        private Long id;
        private String name;
        private int price;
        private int stockQuantity;
        private String author;
        private String isbn;

        BookBuilder() {
        }

        public BookBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BookBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BookBuilder price(int price) {
            this.price = price;
            return this;
        }

        public BookBuilder stockQuantity(int stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Book build() {
            return new Book(id, name, price, stockQuantity, author, isbn);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("BookBuilder{");
            sb.append("id=").append(id);
            sb.append(", name='").append(name).append('\'');
            sb.append(", price=").append(price);
            sb.append(", stockQuantity=").append(stockQuantity);
            sb.append(", author='").append(author).append('\'');
            sb.append(", isbn='").append(isbn).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public static Book of(BookForm form) {
        return new Book().builder()
                        .id(form.getId())
                        .name(form.getName())
                        .price(form.getPrice())
                        .stockQuantity(form.getStockQuantity())
                        .author(form.getAuthor())
                        .isbn(form.getIsbn())
                        .build();
    }
}