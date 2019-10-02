package inflearn.jpa.web.jpashop.repository;

import inflearn.jpa.web.jpashop.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

    public OrderSearch(String memberName, OrderStatus orderStatus) {
        this.memberName = memberName;
        this.orderStatus = orderStatus;
    }
}