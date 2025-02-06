package org.example.java5_asm.controller.customer;

import org.example.java5_asm.model.Product;
import org.example.java5_asm.service.CategoryService;
import org.example.java5_asm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/"})
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String home(Model model) {

        List<Product> discountedProducts = productService.getDiscountedProducts();
        System.out.println("sale ádfgaaasdfasdfasdfasd" + discountedProducts);
        model.addAttribute("discountedProducts", discountedProducts);
        return "customer/home";
    }
}
