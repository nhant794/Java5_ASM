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
import java.util.Optional;

@Controller
@RequestMapping("/detail")
public class DetailProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/{id}")
    public String detailProduct(@PathVariable Long id, Model model) {

        Optional<Product> productDetail = productService.getProductById(id);
        System.out.println("productDetail: " + productDetail);
        model.addAttribute("productDetail", productDetail.get());
        return "customer/detailedProduct";
    }
}
