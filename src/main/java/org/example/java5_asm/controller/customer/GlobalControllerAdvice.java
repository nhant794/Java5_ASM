package org.example.java5_asm.controller.customer;

import org.example.java5_asm.dto.CategoryDTO;
import org.example.java5_asm.service.CategoryService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Component
@ControllerAdvice
public class GlobalControllerAdvice {

    private final CategoryService categoryService;

    public GlobalControllerAdvice(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public List<CategoryDTO> getCategories() {
        // Lấy danh mục cha và các danh mục con (chỉ lấy active = true)
        return categoryService.getParentCategories();
    }
}