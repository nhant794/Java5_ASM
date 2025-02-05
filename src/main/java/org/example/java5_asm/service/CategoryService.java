package org.example.java5_asm.service;

import jakarta.transaction.Transactional;
import org.example.java5_asm.model.Category;
import org.example.java5_asm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    // Phương thức CRUD
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    // Lấy danh sách danh mục cha
    public List<Category> getParentCategories() {
        List<Category> categories = categoryRepository.findByParentIsNull();
        Set<Long> visited = new HashSet<>();
        categories.forEach(category -> loadSubCategories(category, visited));
        return categories;
    }


    private void loadSubCategories(Category category, Set<Long> visited) {
        if (category.getId() == null || visited.contains(category.getId())) {
            return;
        }
        visited.add(category.getId());

        Set<Category> subCategories = new HashSet<>(category.getSubCategories());
        category.setSubCategories(subCategories);
        subCategories.forEach(sub -> loadSubCategories(sub, visited));
    }

    // Lấy danh sách danh mục con của danh mục cha
    public Set<Category> getSubCategories(Long parentId) {
        Optional<Category> parentCategory = categoryRepository.findById(parentId);
        return parentCategory.map(Category::getSubCategories).orElse(new HashSet<>());
    }



}
