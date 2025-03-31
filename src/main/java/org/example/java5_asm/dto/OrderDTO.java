package org.example.java5_asm.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Long userId;
    private Double totalPrice;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderDetailDTO> orderDetails;
}
