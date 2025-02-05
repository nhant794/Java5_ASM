package org.example.java5_asm.service;

import jakarta.transaction.Transactional;
import org.example.java5_asm.model.Category;
import org.example.java5_asm.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

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
}
