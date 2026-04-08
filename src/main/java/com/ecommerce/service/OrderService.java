package com.ecommerce.service;

import com.ecommerce.model.*;
import com.ecommerce.repository.*;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(CartItemRepository cartItemRepository,
                        OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        CartRepository cartRepository,
                        UserRepository userRepository,
                        ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Long cartId, String userEmail) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Ensure the cart belongs to the requesting user.
        if (!cart.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Cart does not belong to user");
        }

        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);

        if (cartItems.isEmpty()) {
            throw new BadRequestException("Cart is empty");
        }

        BigDecimal total = BigDecimal.ZERO;

        // Create the order header first to satisfy FK constraints.
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        order.setUser(user);

        order = orderRepository.save(order);

        for (CartItem item : cartItems) {

            BigDecimal price = item.getProduct().getPrice();
            int qty = item.getQuantity();

            // Validate stock before committing the order line.
            if (item.getProduct().getStockQuantity() < qty) {
                throw new BadRequestException("Insufficient stock for product");
            }

            // Accumulate the order total.
            total = total.add(price.multiply(BigDecimal.valueOf(qty)));

            // Create order line items for each cart item.
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(qty);
            orderItem.setPrice(price);

            orderItemRepository.save(orderItem);

            // Deduct stock immediately after creating the line item.
            Product product = item.getProduct();
            product.setStockQuantity(product.getStockQuantity() - qty);
            productRepository.save(product);
        }

        // Persist the final total after all line items are created.
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);
        // Clear cart items now that they've been converted to an order.
        cartItemRepository.deleteAll(cartItems);

        return savedOrder;
    }

    public List<Order> getOrdersForUser(String userEmail) {

        // Look up user to scope orders to their account.
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return orderRepository.findByUserId(user.getId());
    }

    public Order getOrderForUser(Long orderId, String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        // Prevent access to other users' orders.
        if (!order.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Order does not belong to user");
        }

        return order;
    }
}
