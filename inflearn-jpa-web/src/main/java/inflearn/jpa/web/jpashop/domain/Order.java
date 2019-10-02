package inflearn.jpa.web.jpashop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "order", cascade = ALL)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Order(Long id, Member member, List<OrderItem> orderItems, Delivery delivery, LocalDateTime orderDate, OrderStatus status) {
        this.id = id;
        this.member = member;
        this.orderItems = orderItems;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.status = status;
    }

    private Order(Member member, List<OrderItem> orderItems, Delivery delivery, LocalDateTime orderDate, OrderStatus status) {
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

    private void addOrderItems(OrderItem ... orderItems) {
        for (OrderItem orderItem : orderItems) {
            addOrderItem(orderItem);
        }
    }

    private void addOrderItem(OrderItem orderItem) {
        orderItems.add(new OrderItem().builder()
                                .item(orderItem.getItem())
                                .order(this)
                                .orderPrice(orderItem.getOrderPrice())
                                .count(orderItem.getCount())
                                .build());
    }

    private void setDelivery(Delivery delivery) {
        this.delivery = new Delivery().builder()
                                .order(this)
                                .address(delivery.getAddress())
                                .status(delivery.getStatus())
                                .build();
    }

    /**
     * 주문 생성
     */
    public static Order createOrder(Member member, Delivery delivery, OrderItem ... orderItems) {
        Order order = new Order().builder()
                .member(member)
                .status(OrderStatus.ORDER)
                .orderDate(LocalDateTime.now())
                .build();

        order.setDelivery(delivery);
        order.addOrderItems(orderItems);

        return order;
    }

    public void cancelOrder() {
        if (DeliveryStatus.COMP.equals(delivery.getStatus())) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능 합니다.");
        }

        status = OrderStatus.CANCEL;

        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    /**
     * 주문 가격 조회
     */
    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }

}
