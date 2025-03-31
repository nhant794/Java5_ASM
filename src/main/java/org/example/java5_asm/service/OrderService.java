package org.example.java5_asm.service;

import org.example.java5_asm.model.*;
import org.example.java5_asm.repository.OrderRepository;
import org.example.java5_asm.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Order placeOrder(User user, List<CartItem> cartItems) {
        // Tính tổng giá
        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        // Tạo đơn hàng
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PENDING);

        // Lưu đơn hàng vào database
        Order savedOrder = orderRepository.save(order);

        // Chuyển từ CartItem sang OrderDetail
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            detail.setPrice(item.getProduct().getPrice());
            orderDetailRepository.save(detail);
        }

        return savedOrder;
    }
}
