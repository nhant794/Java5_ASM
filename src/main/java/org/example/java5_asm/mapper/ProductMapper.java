package org.example.java5_asm.mapper;

import org.example.java5_asm.dto.ProductDTO;
import org.example.java5_asm.model.Product;

import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setDiscount(product.getDiscount());
        dto.setImageUrl(product.getImageUrl());
        dto.setStock(product.getStock());
        dto.setCategories(product.getCategories().stream()
                .map(category -> category.getName())
                .collect(Collectors.toList()));
        return dto;
    }
}
