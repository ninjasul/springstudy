package inflearn.jpa.basic.domain.inherited.singletable;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name = "ITEM_SINGLE_TABLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ITEM_TYPE")
public class Item {
    @Id @GeneratedValue
    protected Long id;

    protected String name;

    protected int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Item(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}