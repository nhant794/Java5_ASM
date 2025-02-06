package org.example.java5_asm.controller.customer;

import org.example.java5_asm.model.CartItem;
import org.example.java5_asm.model.User;
import org.example.java5_asm.service.CartService;
import org.example.java5_asm.service.UserService;
import org.example.java5_asm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        cartService.addToCart(user.getId(), productId, quantity);
        return "redirect:/cart";
    }

    @GetMapping
    public String viewCart(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<CartItem> cartItems = cartService.getCartItems(user.getId());
        System.out.println("sản phẩm : " + cartItems);
        model.addAttribute("cartItems", cartItems);
        return "customer/cart";
    }
}
