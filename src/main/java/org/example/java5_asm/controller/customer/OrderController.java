package org.example.java5_asm.controller.customer;

import org.example.java5_asm.model.CartItem;
import org.example.java5_asm.model.Order;
import org.example.java5_asm.model.OrderStatus;
import org.example.java5_asm.model.User;
import org.example.java5_asm.repository.CartRepository;
import org.example.java5_asm.repository.OrderRepository;
import org.example.java5_asm.service.OrderService;
import org.example.java5_asm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    // Hiển thị trang xác nhận đơn hàng
    @GetMapping("/confirm")
    public String showConfirmPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<CartItem> cartItems = cartRepository.findByUser(user);

        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "customer/confirm";
    }

    // Xử lý đặt hàng
    @PostMapping("/checkout")
    public String processOrder(Order order, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(java.time.LocalDateTime.now());

        // Lưu đơn hàng vào database
         orderRepository.save(order);

        return "/customer/order_success";
    }
}
