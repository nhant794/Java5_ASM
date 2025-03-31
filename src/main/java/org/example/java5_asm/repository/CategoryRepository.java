package org.example.java5_asm.repository;

import org.example.java5_asm.model.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @EntityGraph(attributePaths = {"subCategories"})
    List<Category> findByParentIsNull();

    @EntityGraph(attributePaths = {"subCategories"})
    List<Category> findByParentIsNullAndActive(boolean active);

    List<Category> findByParentId(Long parentId);

    List<Category> findByParentIdAndActive(Long parentId, boolean active);
}