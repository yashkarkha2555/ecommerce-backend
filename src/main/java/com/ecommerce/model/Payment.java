package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key for the payment record.
    private Long id;

    @OneToOne
    @JoinColumn(name="order_id")
    // The order this payment is for.
    private Order order;

    // Amount captured for the payment.
    private BigDecimal amount;

    // Payment status string (e.g., PAID).
    private String status;

    // Payment method (e.g., CARD, UPI).
    private String method;

    // Timestamp when the payment was created.
    private LocalDateTime createdAt;
}
