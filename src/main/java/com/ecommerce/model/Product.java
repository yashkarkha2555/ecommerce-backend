package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key for the product.
    private Long id;

    @NotBlank(message = "Product name is required")
    // Human-readable product name.
    private String name;

    @NotBlank(message = "Description is required")
    // Product description for catalog details.
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "1.0", message = "Price must be greater than 0")
    // Unit price for the product.
    private BigDecimal price;

    @Min(value = 0, message = "Stock cannot be negative")
    // Available inventory units.
    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    // Optional category that groups products.
    private Category category;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
