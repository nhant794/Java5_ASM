package org.example.java5_asm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> subCategories = new HashSet<>();

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    // Thuộc tính level để xác định cấp của danh mục
    @Column(name = "level", nullable = false)
    private int level;

    // Trạng thái active: true (hoạt động), false (không hoạt động)
    @Column(name = "active", nullable = false)
    private boolean active;

    // Phương thức tiện ích để kiểm tra vai trò của danh mục
    @Transient
    public boolean isRoot() {
        return parent == null;
    }

    @Transient
    public boolean hasSubCategories() {
        return !subCategories.isEmpty();
    }
}