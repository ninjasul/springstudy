package inflearn.jpa.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Item {
    @Id @GeneratedValue
    protected Long id;

    protected String name;
    protected int price;
    protected int stockQuantity;

    @OneToMany(mappedBy = "item")
    protected List<ItemCategory> itemCategories = new ArrayList<>();
}