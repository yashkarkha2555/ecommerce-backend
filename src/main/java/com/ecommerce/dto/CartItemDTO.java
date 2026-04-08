package com.ecommerce.dto;

import java.math.BigDecimal;

public class CartItemDTO {

    // Cart item identifier.
    private Long id;
    // Product id for this line.
    private Long productId;
    // Product name for display.
    private String productName;
    // Unit price at the time of cart view.
    private BigDecimal price;
    // Quantity of the product in the cart.
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
