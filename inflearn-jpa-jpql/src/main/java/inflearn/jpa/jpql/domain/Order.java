package inflearn.jpa.jpql.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue
    private Long id;

    private int orderAmount;

    @Embedded
    private Address address;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public Order(Long id, int orderAmount, Address address, Member member, Product product) {
        this.id = id;
        this.orderAmount = orderAmount;
        this.address = address;
        this.member = member;
        this.product = product;
    }

    public Order(int orderAmount, Address address, Member member, Product product) {
        this.orderAmount = orderAmount;
        this.address = address;
        this.member = member;
        this.product = product;
    }
}