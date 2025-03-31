package org.example.java5_asm.controller.admin;

import org.example.java5_asm.dto.CategoryDTO;
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
        // Lấy danh sách danh mục cha (active = true)
        List<CategoryDTO> parentCategories = categoryService.getParentCategories();
        // Lấy danh sách danh mục con (active = true)
        List<CategoryDTO> subCategories = (parentId != null) ? categoryService.getSubCategories(parentId) : List.of();

        // Lấy thông tin danh mục cha (nếu có)
        Category parentCategory = (parentId != null) ? categoryService.findById(parentId).orElse(null) : null;

        // Tạo một CategoryDTO mới để dùng trong form
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setActive(true); // Mặc định active = true khi tạo mới

        model.addAttribute("categories", parentCategories);
        model.addAttribute("subCategories", subCategories);
        model.addAttribute("category", categoryDTO);
        model.addAttribute("parentCategory", parentCategory);
        return "admin/category";
    }

    // Hiển thị form thêm mới danh mục
    @GetMapping("/add")
    public String showAddForm(Model model) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setActive(true); // Mặc định active = true khi tạo mới
        model.addAttribute("category", categoryDTO);
        model.addAttribute("parentCategories", categoryService.getParentCategories());
        return "admin/category";
    }

    // Xử lý thêm mới danh mục
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") CategoryDTO categoryDTO,
                               @RequestParam(value = "parentId", required = false) Long parentId) {
        // Chuyển từ CategoryDTO sang Category
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setLevel(categoryDTO.getLevel());
        category.setActive(categoryDTO.isActive());

        // Gán danh mục cha (nếu có)
        if (parentId != null) {
            Optional<Category> parentCategory = categoryService.findById(parentId);
            parentCategory.ifPresent(category::setParent);
        }

        // Lưu danh mục
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    // Hiển thị form cập nhật danh mục
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Category> categoryOpt = categoryService.findById(id);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            // Chuyển từ Category sang CategoryDTO
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            categoryDTO.setParentId(category.getParent() != null ? category.getParent().getId() : null);
            categoryDTO.setLevel(category.getLevel());
            categoryDTO.setActive(category.isActive());

            model.addAttribute("category", categoryDTO);
            model.addAttribute("categories", categoryService.getParentCategories());
            model.addAttribute("subCategories", categoryService.getSubCategories(category.getParent() != null ? category.getParent().getId() : null));
            model.addAttribute("parentCategory", category.getParent());
            return "admin/category";
        }
        return "redirect:/admin/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        categoryService.delete(id); // Sử dụng soft delete (đặt active = false)
        return "redirect:/admin/category";
    }
}