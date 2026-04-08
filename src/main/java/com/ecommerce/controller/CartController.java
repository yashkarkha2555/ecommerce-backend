package com.ecommerce.controller;

import com.ecommerce.dto.AddToCartRequest;
import com.ecommerce.dto.CartDTO;
import com.ecommerce.dto.UpdateCartItemRequest;
import com.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public CartDTO getCart(@AuthenticationPrincipal String email) {
        // Return the current user's cart, creating one if missing.
        return cartService.getCart(email);
    }

    @PostMapping("/items")
    public CartDTO addToCart(@AuthenticationPrincipal String email,
                             @Valid @RequestBody AddToCartRequest request) {
        // Add a product to the cart or increase quantity if already present.
        return cartService.addToCart(email, request.getProductId(), request.getQuantity());
    }

    @PutMapping("/items")
    public CartDTO updateQuantity(@AuthenticationPrincipal String email,
                                  @Valid @RequestBody UpdateCartItemRequest request) {
        // Update quantity for an existing cart item.
        return cartService.updateQuantity(email, request.getCartItemId(), request.getQuantity());
    }

    @DeleteMapping("/items/{id}")
    public CartDTO removeItem(@AuthenticationPrincipal String email,
                              @PathVariable Long id) {
        // Remove a specific item from the cart.
        return cartService.removeItem(email, id);
    }

}
