package com.ecommerce.dto;

import java.math.BigDecimal;

public class OrderItemDTO {

    // Product id for this line item.
    private Long productId;
    // Quantity purchased.
    private Integer quantity;
    // Unit price at purchase time.
    private BigDecimal price;

    public OrderItemDTO() {}

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
