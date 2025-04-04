package org.example.java5_asm.controller.admin;


import org.example.java5_asm.dto.CategoryDTO;
import org.example.java5_asm.model.Category;
import org.example.java5_asm.model.Product;
import org.example.java5_asm.service.CategoryService;
import org.example.java5_asm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductAdminController {


    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductAdminController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showProductForm(Model model) {
        // Lấy danh sách danh mục cha (chỉ lấy active = true, đã bao gồm danh mục con)
        List<CategoryDTO> categories = categoryService.getParentCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("product", new Product());
        System.out.println("anhewqffffffffffffffff: " + productService.getAllProducts());

        return "admin/product";
    }




    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/product";
        }

        productService.saveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "admin/product";
        } else {
            return "redirect:/admin/product";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product";
    }



}
