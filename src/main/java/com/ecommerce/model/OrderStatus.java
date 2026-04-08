package com.ecommerce.model;

public enum OrderStatus {
    // Order has been created but not paid.
    CREATED,
    // Payment completed.
    PAID,
    // Order has left the warehouse.
    SHIPPED,
    // Order delivered to the customer.
    DELIVERED,
    // Order cancelled before completion.
    CANCELLED
}
