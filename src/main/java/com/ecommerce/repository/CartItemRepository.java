package com.ecommerce.repository;

import com.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Load all cart items for a cart.
    List<CartItem> findByCartId(Long cartId);
    // Find a specific cart item by cart and product.
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
}
