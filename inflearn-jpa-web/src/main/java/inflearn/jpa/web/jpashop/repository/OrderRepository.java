package inflearn.jpa.web.jpashop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inflearn.jpa.web.jpashop.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    QOrder order = QOrder.order;
    QMember member = QMember.member;

    @Autowired
    public OrderRepository(JPAQueryFactory queryFactory, EntityManager em) {
        super(Order.class);
        this.queryFactory = queryFactory;
        this.em = em;
    }

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        return queryFactory.select(order)
                            .from(order)
                            .join(order.member, member)
                            .where(statusEq(orderSearch.getOrderStatus()),
                                    nameLike(orderSearch.getMemberName()))
                            .limit(1000)
                            .fetch();
    }

    private BooleanExpression statusEq(OrderStatus statusCond) {
        if (statusCond == null) {
            return null;
        }

        return order.status.eq(statusCond);
    }

    private BooleanExpression nameLike(String nameCond) {
        if(!StringUtils.hasText(nameCond)) {
            return null;
        }

        return member.name.like(nameCond);
    }
}