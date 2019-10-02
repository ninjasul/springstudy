package inflearn.jpa.web.jpashop.domain.item;

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

    protected Book(String name, int price, int stockQuantity, String author, String isbn) {
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
        private String name;
        private int price;
        private int stockQuantity;
        private String author;
        private String isbn;

        BookBuilder() {
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
            return new Book(name, price, stockQuantity, author, isbn);
        }

        public String toString() {
            final StringBuilder sb = new StringBuilder("BookBuilder{");
            sb.append("name='").append(name).append('\'');
            sb.append(", price=").append(price);
            sb.append(", stockQuantity=").append(stockQuantity);
            sb.append(", author='").append(author).append('\'');
            sb.append(", isbn='").append(isbn).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}