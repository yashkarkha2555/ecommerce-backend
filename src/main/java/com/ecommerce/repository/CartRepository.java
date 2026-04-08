package com.ecommerce.repository;

import com.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // Retrieve a cart by its owning user.
    Optional<Cart> findByUserId(Long userId);
    // Fast existence check to avoid fetching the full cart.
    boolean existsByUserId(Long userId);
}
