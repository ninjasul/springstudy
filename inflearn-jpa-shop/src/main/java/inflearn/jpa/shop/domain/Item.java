package inflearn.jpa.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Item {
    @Id
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
}