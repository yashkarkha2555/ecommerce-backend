package com.ecommerce.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductDTO {

    // Product identifier.
    private Long id;
    @NotBlank
    // Product name displayed to users.
    private String name;
    @NotBlank
    // Product description text.
    private String description;
    @NotNull
    @DecimalMin(value = "0.01")
    // Product unit price.
    private BigDecimal price;
    @NotNull
    @Min(0)
    // Available stock quantity.
    private Integer stockQuantity;
    // Category id for associating with a category.
    private Long categoryId;

    public ProductDTO() {}

    public ProductDTO(Long id, String name, String description,
                      BigDecimal price, Integer stockQuantity, Long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}
