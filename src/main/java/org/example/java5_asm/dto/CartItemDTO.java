package org.example.java5_asm.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
}
