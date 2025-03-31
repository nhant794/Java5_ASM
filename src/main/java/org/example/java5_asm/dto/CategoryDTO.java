package org.example.java5_asm.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private Long parentId;
    private int level; // Thêm level
    private boolean active; // Thêm active
    private List<CategoryDTO> subCategories = new ArrayList<>(); // Thay subCategoryIds bằng subCategories
}