package org.example.java5_asm.controller.customer;


import org.example.java5_asm.entity.Category;
import org.example.java5_asm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String showHome(Model model) {
        List<Category> categories = categoryRepository.findByParentIdIsNull();
        model.addAttribute("categories", categories);
        System.out.println("danh sách: " + categories);
        return "customer/Header";
    }
}
