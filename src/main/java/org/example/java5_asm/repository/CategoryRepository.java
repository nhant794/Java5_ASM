package org.example.java5_asm.repository;

import org.example.java5_asm.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParentIdIsNull(); // Lấy danh mục cha
    List<Category> findByParentId(Long parentId); // Lấy danh mục con
}
