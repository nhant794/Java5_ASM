package org.example.java5_asm.service;

import jakarta.transaction.Transactional;
import org.example.java5_asm.model.Order;
import org.example.java5_asm.model.OrderStatus;
import org.example.java5_asm.model.Payment;
import org.example.java5_asm.repository.OrderRepository;
import org.example.java5_asm.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void completePayment(Long orderId, String paymentMethod) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Tạo thông tin thanh toán
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus("PAID");
        paymentRepository.save(payment);

        // Cập nhật trạng thái đơn hàng
        order.setStatus(OrderStatus.PENDING);
        orderRepository.save(order);
    }
}
