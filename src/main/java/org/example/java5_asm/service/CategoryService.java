package org.example.java5_asm.service;

import jakarta.transaction.Transactional;
import org.example.java5_asm.dto.CategoryDTO;
import org.example.java5_asm.model.Category;
import org.example.java5_asm.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Phương thức CRUD
    public Category save(Category category) {
        // Tính toán level khi lưu danh mục
        if (category.getParent() != null) {
            category.setLevel(category.getParent().getLevel() + 1);
        } else {
            category.setLevel(0);
        }
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void delete(Long id) {
        // Thay vì xóa cứng, có thể dùng soft delete bằng cách đặt active = false
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            category.setActive(false);
            categoryRepository.save(category);
        }
    }

    // Lấy danh sách danh mục cha (chỉ lấy các danh mục active)
    public List<CategoryDTO> getParentCategories() {
        List<Category> categories = categoryRepository.findByParentIsNullAndActive(true);
        Set<Long> visited = new HashSet<>();
        List<CategoryDTO> categoryDTOs = categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        categoryDTOs.forEach(dto -> loadSubCategories(dto, visited));
        return categoryDTOs;
    }

    // Tải danh mục con (chỉ lấy các danh mục active)
    private void loadSubCategories(CategoryDTO categoryDTO, Set<Long> visited) {
        if (categoryDTO.getId() == null || visited.contains(categoryDTO.getId())) {
            return;
        }
        visited.add(categoryDTO.getId());

        List<Category> subCategories = categoryRepository.findByParentIdAndActive(categoryDTO.getId(), true);
        List<CategoryDTO> subCategoryDTOs = subCategories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        categoryDTO.setSubCategories(subCategoryDTOs);
        subCategoryDTOs.forEach(sub -> loadSubCategories(sub, visited));
    }

    // Lấy danh sách danh mục con của danh mục cha (chỉ lấy các danh mục active)
    public List<CategoryDTO> getSubCategories(Long parentId) {
        return categoryRepository.findByParentIdAndActive(parentId, true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Chuyển từ Category sang CategoryDTO
    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setParentId(category.getParent() != null ? category.getParent().getId() : null);
        dto.setLevel(category.getLevel());
        dto.setActive(category.isActive());
        return dto;
    }
}