package com.ecommerce.repository;

import com.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Name search used by product search endpoint.
    List<Product> findByNameContainingIgnoreCase(String name);

    // Filter products by price range.
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Filter products by category id.
    List<Product> findByCategoryId(Long categoryId);
}
