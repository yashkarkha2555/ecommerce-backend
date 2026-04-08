package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key for the cart.
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    // The user who owns this cart.
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    // Items currently in the cart.
    private List<CartItem> items = new ArrayList<>();
}
