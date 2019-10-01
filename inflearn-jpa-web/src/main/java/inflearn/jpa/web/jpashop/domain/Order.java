package inflearn.jpa.web.jpashop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@Getter
@Builder
@Table(name = "orders")
@Entity
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(Long id, Member member, List<OrderItem> orderItems, Delivery delivery, LocalDateTime orderDate, OrderStatus status) {
        this.id = id;
        this.member = member;
        this.orderItems = orderItems;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Order(Member member, List<OrderItem> orderItems, Delivery delivery, LocalDateTime orderDate, OrderStatus status) {
        this.member = member;
        this.orderItems = orderItems;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.status = status;
    }

    public void setMember(Member member) {

        member.getOrders().add(this);

        this.member = new Member().builder()
                            .name(member.getName())
                            .address(member.getAddress())
                            .orders(member.getOrders())
                            .build();
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(new OrderItem().builder()
                                .item(orderItem.getItem())
                                .order(this)
                                .orderPrice(orderItem.getOrderPrice())
                                .count(orderItem.getCount())
                                .build());
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = new Delivery().builder()
                                .order(this)
                                .address(delivery.getAddress())
                                .status(delivery.getStatus())
                                .build();
    }
}
