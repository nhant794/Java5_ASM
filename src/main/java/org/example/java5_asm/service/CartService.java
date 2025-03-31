package org.example.java5_asm.service;

import org.example.java5_asm.model.CartItem;
import org.example.java5_asm.model.User;
import org.example.java5_asm.model.Product;
import org.example.java5_asm.repository.CartRepository;
import org.example.java5_asm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    public double getCartTotal(User user) {
        return getCartItems(user).stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void updateCart(User user, Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            System.out.println("Sản phẩm không tồn tại!");
            return;
        }

        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product)
                .orElse(new CartItem(null, user, product, 0));

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItemRepository.save(cartItem);
    }

    public void removeFromCart(User user, Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            System.out.println("Sản phẩm không tồn tại!");
            return;
        }

        cartItemRepository.findByUserAndProduct(user, product).ifPresent(cartItemRepository::delete);
    }
}
