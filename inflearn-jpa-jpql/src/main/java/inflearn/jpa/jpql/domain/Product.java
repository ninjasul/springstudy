package inflearn.jpa.jpql.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Builder
@Entity
public class Product {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

    private int stockAmount;

    public Product(Long id, String name, int price, int stockAmount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockAmount = stockAmount;
    }

    public Product(String name, int price, int stockAmount) {
        this.name = name;
        this.price = price;
        this.stockAmount = stockAmount;
    }
}