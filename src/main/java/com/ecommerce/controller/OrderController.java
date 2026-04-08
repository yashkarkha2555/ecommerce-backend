package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestParam Long cartId,
                             @AuthenticationPrincipal String email) {
        // Convert the user's cart into an order.
        return orderService.createOrder(cartId, email);
    }

    @GetMapping
    public List<Order> getAllOrders(@AuthenticationPrincipal String email) {
        // List all orders belonging to the current user.
        return orderService.getOrdersForUser(email);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id,
                              @AuthenticationPrincipal String email) {
        // Fetch a single order, validating ownership.
        return orderService.getOrderForUser(id, email);
    }
}
