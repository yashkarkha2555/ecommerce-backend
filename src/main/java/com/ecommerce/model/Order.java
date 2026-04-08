package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key for the order.
    private Long id;

    // Total cost across all order items.
    private BigDecimal totalAmount;

    // Timestamp when the order was created.
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    // Current lifecycle status of the order.
    private OrderStatus status;

    @ManyToOne
    @JsonIgnore
    // Owning user for this order.
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    // Line items that make up this order.
    private List<OrderItem> items = new ArrayList<>();
}
