package org.example.java5_asm.repository;

import org.example.java5_asm.model.Order;
import org.example.java5_asm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
