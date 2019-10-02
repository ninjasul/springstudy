package inflearn.jpa.web.jpashop.service;

import inflearn.jpa.web.jpashop.domain.Address;
import inflearn.jpa.web.jpashop.domain.Member;
import inflearn.jpa.web.jpashop.domain.Order;
import inflearn.jpa.web.jpashop.domain.item.Book;
import inflearn.jpa.web.jpashop.domain.item.Item;
import inflearn.jpa.web.jpashop.exception.NotEnoughStockException;
import inflearn.jpa.web.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static inflearn.jpa.web.jpashop.domain.OrderStatus.CANCEL;
import static inflearn.jpa.web.jpashop.domain.OrderStatus.ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        // given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10000, 10);

        // when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order foundOrder = orderRepository.findOne(orderId);

        assertThat(foundOrder.getStatus()).isEqualTo(ORDER);
        assertThat(foundOrder.getOrderItems().size()).isOne();
        assertThat(foundOrder.getTotalPrice()).isEqualTo(10000 * orderCount);
        assertThat(book.getStockQuantity()).isEqualTo(8);
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() {
        // given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10000, 10);
        int orderCount = 11;

        // when
        orderService.order(member.getId(), book.getId(), orderCount);

        // then
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }

    @Test
    public void 주문취소() {
        // given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10000, 10);

        int orderCount = 5;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // when
        orderService.cancelOrder(orderId);

        // then
        Order foundOrder = orderRepository.findOne(orderId);

        assertThat(foundOrder.getStatus()).isEqualTo(CANCEL);
        assertThat(book.getStockQuantity()).isEqualTo(10);
    }

    private Member createMember() {
        Member member = new Member().builder()
                .name("회원1")
                .address(new Address().builder()
                        .city("서울")
                        .street("강가")
                        .zipcode("123-123")
                        .build())
                .build();

        em.persist(member);

        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book().builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();

        em.persist(book);

        return book;
    }
}