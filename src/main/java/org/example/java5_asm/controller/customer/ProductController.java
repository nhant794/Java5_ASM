package org.example.java5_asm.controller.customer;

import org.example.java5_asm.model.Product;
import org.example.java5_asm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{categoryId}")
    public String product(@PathVariable Long categoryId, Model model) {
        List<Product> productList = productService.getProductsByCategory(categoryId);
        System.out.println("product list : " + productList);
        model.addAttribute("productList", productList);

        return "customer/products";
    }
}
