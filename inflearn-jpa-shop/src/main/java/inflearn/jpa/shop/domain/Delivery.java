package inflearn.jpa.shop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Builder
@Entity
public class Delivery {

    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private String city;
    private String street;
    private String zipCode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public Delivery(Long id, Order order, String city, String street, String zipCode, DeliveryStatus status) {
        this.id = id;
        this.order = order;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.status = status;
    }

    public Delivery(Order order, String city, String street, String zipCode, DeliveryStatus status) {
        this.order = order;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.status = status;
    }
}