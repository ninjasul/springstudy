package inflearn.jpa.web.jpashop.controller;

import inflearn.jpa.web.jpashop.domain.Member;
import inflearn.jpa.web.jpashop.domain.Order;
import inflearn.jpa.web.jpashop.domain.item.Item;
import inflearn.jpa.web.jpashop.repository.OrderSearch;
import inflearn.jpa.web.jpashop.service.ItemService;
import inflearn.jpa.web.jpashop.service.MemberService;
import inflearn.jpa.web.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderService orderService;

    @GetMapping("/order")
    public String createOrderForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam Long memberId, @RequestParam Long itemId, @RequestParam int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orders(@ModelAttribute OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}