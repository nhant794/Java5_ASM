package org.example.java5_asm.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String cart() {
        return "customer/cart";
    }
}
