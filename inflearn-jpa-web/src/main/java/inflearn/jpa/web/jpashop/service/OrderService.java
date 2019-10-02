package inflearn.jpa.web.jpashop.service;

import inflearn.jpa.web.jpashop.domain.Delivery;
import inflearn.jpa.web.jpashop.domain.Member;
import inflearn.jpa.web.jpashop.domain.Order;
import inflearn.jpa.web.jpashop.domain.OrderItem;
import inflearn.jpa.web.jpashop.domain.item.Item;
import inflearn.jpa.web.jpashop.repository.ItemRepository;
import inflearn.jpa.web.jpashop.repository.MemberRepository;
import inflearn.jpa.web.jpashop.repository.OrderRepository;
import inflearn.jpa.web.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery().builder()
                                        .address(member.getAddress())
                                        .build();

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancelOrder();
    }

    /*public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }*/
}