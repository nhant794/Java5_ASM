package org.example.java5_asm.repository;

import org.example.java5_asm.model.CartItem;
import org.example.java5_asm.model.Product;
import org.example.java5_asm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndProduct(User user, Product product);
}

