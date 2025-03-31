package org.example.java5_asm.controller.customer;

import org.example.java5_asm.dto.CategoryDTO;
import org.example.java5_asm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    @ResponseBody
    public List<CategoryDTO> getCategories() {
        return categoryService.getParentCategories();
    }
}