package org.example.java5_asm.mapper;

import org.example.java5_asm.dto.OrderDetailDTO;
import org.example.java5_asm.model.OrderDetail;

public class OrderDetailMapper {
    public static OrderDetailDTO toDTO(OrderDetail orderDetail) {
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setId(orderDetail.getId());
        dto.setOrderId(orderDetail.getOrder().getId());
        dto.setProductId(orderDetail.getProduct().getId());
        dto.setQuantity(orderDetail.getQuantity());
        dto.setPrice(orderDetail.getPrice());
        return dto;
    }
}
