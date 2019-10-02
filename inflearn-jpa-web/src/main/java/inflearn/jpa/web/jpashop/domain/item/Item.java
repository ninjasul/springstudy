package inflearn.jpa.web.jpashop.domain.item;

import inflearn.jpa.web.jpashop.domain.Category;
import inflearn.jpa.web.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    public Item(Long id, String name, int price, int stockQuantity, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categories = categories;
    }

    public Item(String name, int price, int stockQuantity, List<Category> categories) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categories = categories;
    }

    /**
     * stock 증가
     */
    public void addStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void reduceStockQuantity(int quantity) {
        this.stockQuantity -= quantity;

        if (this.stockQuantity < 0) {
            this.stockQuantity += quantity;
            throw new NotEnoughStockException("needs more stock");
        }
    }
}