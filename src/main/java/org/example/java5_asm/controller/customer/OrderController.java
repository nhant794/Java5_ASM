package org.example.java5_asm.controller.customer;

import org.example.java5_asm.model.User;
import org.example.java5_asm.service.OrderService;
import org.example.java5_asm.service.UserService;
import org.example.java5_asm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/checkout")
    public String checkout(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        orderService.checkout(user.getId());
        return "redirect:/order/success";
    }

    @GetMapping("/success")
    public String orderSuccess() {
        return "customer/order_success";
    }
}

