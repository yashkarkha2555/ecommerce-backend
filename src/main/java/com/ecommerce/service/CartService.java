package com.ecommerce.service;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.dto.CartItemDTO;
import com.ecommerce.model.*;
import com.ecommerce.repository.*;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       ProductRepository productRepository,
                       UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public CartDTO getCart(String email){

        // Ensure a cart exists for the user and return its current state.
        Cart cart = getOrCreateCart(email);
        return mapToCartDTO(cart);
    }

    public CartDTO addToCart(String email, Long productId, int quantity){

        Cart cart = getOrCreateCart(email);

        // Validate product existence before adding.
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // If item already exists in cart, increment its quantity.
        Optional<CartItem> existingItem =
                cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

        CartItem item;
        if (existingItem.isPresent()) {
            item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            // Otherwise create a new cart item.
            item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(quantity);
        }

        cartItemRepository.save(item);

        return mapToCartDTO(cart);
    }

    public CartDTO updateQuantity(String email, Long cartItemId, int quantity){

        Cart cart = getOrCreateCart(email);

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        // Prevent users from changing another user's cart items.
        if (!item.getCart().getId().equals(cart.getId())) {
            throw new BadRequestException("Cart item does not belong to user");
        }

        item.setQuantity(quantity);
        cartItemRepository.save(item);

        return mapToCartDTO(cart);
    }

    public CartDTO removeItem(String email, Long cartItemId){

        Cart cart = getOrCreateCart(email);

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        // Prevent users from deleting another user's cart items.
        if (!item.getCart().getId().equals(cart.getId())) {
            throw new BadRequestException("Cart item does not belong to user");
        }

        cartItemRepository.delete(item);

        return mapToCartDTO(cart);
    }

    private Cart getOrCreateCart(String email) {

        // Resolve user by email first.
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Reuse existing cart if present; otherwise create a new one.
        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    private CartDTO mapToCartDTO(Cart cart) {

        // Load cart items explicitly to build a response DTO.
        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());

        List<CartItemDTO> itemDTOs = items.stream().map(item -> {
            // Flatten product details into the cart item DTO.
            CartItemDTO dto = new CartItemDTO();
            dto.setId(item.getId());
            dto.setProductId(item.getProduct().getId());
            dto.setProductName(item.getProduct().getName());
            dto.setPrice(item.getProduct().getPrice());
            dto.setQuantity(item.getQuantity());
            return dto;
        }).toList();

        // Calculate cart total from item price * quantity.
        BigDecimal total = itemDTOs.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        dto.setItems(itemDTOs);
        dto.setTotal(total);

        return dto;
    }
}
