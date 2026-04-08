package com.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDTO {

    // Cart identifier.
    private Long id;
    // Owning user id.
    private Long userId;
    // Line items in the cart.
    private List<CartItemDTO> items;
    // Computed cart total.
    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
