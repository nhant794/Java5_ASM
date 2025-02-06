package org.example.java5_asm.repository;

import org.example.java5_asm.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParentIsNull(); // Lấy danh mục cha (cấp cao nhất)
    List<Category> findByParentId(Long parentId);
}
