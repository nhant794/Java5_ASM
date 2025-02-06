package org.example.java5_asm.service;

import jakarta.transaction.Transactional;
import org.example.java5_asm.model.CartItem;
import org.example.java5_asm.model.Order;
import org.example.java5_asm.model.OrderDetail;
import org.example.java5_asm.model.User;
import org.example.java5_asm.repository.CartRepository;
import org.example.java5_asm.repository.OrderDetailRepository;
import org.example.java5_asm.repository.OrderRepository;
import org.example.java5_asm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void checkout(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Giỏ hàng trống, không thể thanh toán");
        }

        // Tính tổng giá trị đơn hàng
        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        // Tạo đơn hàng mới
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(totalPrice);
        order.setStatus("PENDING"); // Chờ thanh toán
        order.setCreatedAt(LocalDateTime.now());
        order = orderRepository.save(order);

        // Chuyển CartItem sang OrderDetail
        for (CartItem item : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(item.getProduct());
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setPrice(item.getProduct().getPrice());
            orderDetailRepository.save(orderDetail);
        }

        // Xóa giỏ hàng sau khi đặt hàng
        cartItemRepository.deleteAll(cartItems);
    }
}

