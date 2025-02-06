package org.example.java5_asm.controller.admin;

import org.example.java5_asm.model.Category;
import org.example.java5_asm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/admin/category")
public class CategoryAdminController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(@RequestParam(value = "parentId", required = false) Long parentId, Model model) {
        List<Category> parentCategories = categoryService.getParentCategories();
        List<Category> subCategories = (parentId != null) ? categoryService.getSubCategories(parentId) : List.of();

        Category parentCategory = (parentId != null) ? categoryService.findById(parentId).orElse(null) : null;

        model.addAttribute("categories", parentCategories);
        model.addAttribute("subCategories", subCategories);
        model.addAttribute("category", new Category());
        model.addAttribute("parentCategory", parentCategory); // Truyền thông tin danh mục cha
        return "admin/category";
    }

    // Hiển thị form thêm mới danh mục
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("parentCategories", categoryService.getParentCategories());
        return "admin/category";
    }

    // Xử lý thêm mới danh mục
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category,
                               @RequestParam(value = "parentId", required = false) Long parentId) {
        if (parentId != null) {
            Optional<Category> parentCategory = categoryService.findById(parentId);
            parentCategory.ifPresent(category::setParent); // Gán danh mục cha
        }
        categoryService.save(category);
        return "redirect:/admin/category";
    }


    // Hiển thị form cập nhật danh mục
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            model.addAttribute("categories", categoryService.getParentCategories());
            model.addAttribute("subCategories", categoryService.getSubCategories(category.get().getParent() != null ? category.get().getParent().getId() : null));
            model.addAttribute("parentCategory", category.get().getParent());
            return "admin/category";
        }
        return "redirect:/admin/category";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        System.out.println("ìaasfasfa: " +id);
        categoryService.delete(id);
        return "redirect:/admin/category";
    }

}
