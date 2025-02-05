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

    // Hiển thị danh sách danh mục, bao gồm danh mục con
    @GetMapping
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getParentCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category()); // Thêm dòng này
        model.addAttribute("parentCategories", categoryService.getParentCategories()); // Đảm bảo luôn có dữ liệu
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
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    // Hiển thị form cập nhật danh mục
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            model.addAttribute("parentCategories", categoryService.getParentCategories());
            return "admin/category";
        }
        return "redirect:/admin/category";
    }

    // Xử lý xóa danh mục
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/admin/category";
    }
}
