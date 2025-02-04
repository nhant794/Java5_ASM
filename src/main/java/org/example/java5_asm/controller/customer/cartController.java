package org.example.java5_asm.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class cartController {

    @GetMapping("/cart")
    public String cart(Model model) {
        return "customer/cart";
    }
}
