package com.ecommerce.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key for the order line item.
    private Long id;

    @ManyToOne
    @JsonIgnore
    // Owning order for this line item.
    private Order order;

    @ManyToOne
    // Product purchased.
    private Product product;

    // Quantity purchased.
    private int quantity;

    // Snapshot of the unit price at purchase time.
    private BigDecimal price;
}
