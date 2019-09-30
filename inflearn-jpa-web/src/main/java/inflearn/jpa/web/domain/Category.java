package inflearn.jpa.web.domain;

import inflearn.jpa.web.domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public Category(Long id, String name, List<Item> items, Category parent, List<Category> child) {
        this.id = id;
        this.name = name;
        this.items = items;
        this.parent = parent;
        this.child = child;
    }

    public Category(String name, List<Item> items, Category parent, List<Category> child) {
        this.name = name;
        this.items = items;
        this.parent = parent;
        this.child = child;
    }
}
