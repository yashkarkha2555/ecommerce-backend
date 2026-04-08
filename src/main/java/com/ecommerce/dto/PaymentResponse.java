package com.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {

    // Payment identifier.
    private Long id;
    // Order identifier this payment belongs to.
    private Long orderId;
    // Amount paid.
    private BigDecimal amount;
    // Current payment status.
    private String status;
    // Payment method used.
    private String method;
    // Timestamp when payment was created.
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
