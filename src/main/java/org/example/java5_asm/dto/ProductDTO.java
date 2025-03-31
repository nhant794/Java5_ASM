package org.example.java5_asm.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double discount;
    private String imageUrl;
    private Integer stock;
    private List<String> categories;
}
