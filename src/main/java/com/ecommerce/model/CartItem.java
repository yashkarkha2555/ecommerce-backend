package com.ecommerce.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key for the cart item.
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    // Product referenced by this cart item.
    private Product product;

    // Quantity of the product in the cart.
    private Integer quantity;

    @ManyToOne
    @JsonIgnore
    // Back-reference to the owning cart.
    private Cart cart;

}
