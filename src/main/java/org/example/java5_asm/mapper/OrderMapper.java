package org.example.java5_asm.mapper;

import org.example.java5_asm.dto.OrderDTO;
import org.example.java5_asm.dto.OrderDetailDTO;
import org.example.java5_asm.model.Order;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus().toString());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setOrderDetails(order.getOrderDetails().stream()
                .map(OrderDetailMapper::toDTO)
                .collect(Collectors.toList()));
        return dto;
    }
}
