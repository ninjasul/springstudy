package inflearn.jpa.web.jpashop.domain;

import inflearn.jpa.web.jpashop.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@Getter
@Builder
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

    @ManyToOne(fetch = LAZY)
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

    public void addChildCategory(Category child) {
        this.child.add(
                new Category().builder()
                    .name(child.getName())
                    .items(child.getItems())
                    .parent(this)
                    .child(child.getChild())
                    .build()
        );
    }
}
