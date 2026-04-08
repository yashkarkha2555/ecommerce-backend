package com.ecommerce.controller;

import com.ecommerce.dto.PaymentRequest;
import com.ecommerce.dto.PaymentResponse;
import com.ecommerce.model.Payment;
import com.ecommerce.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponse createPayment(@AuthenticationPrincipal String email,
                                         @Valid @RequestBody PaymentRequest request) {
        // Create a payment for the given order and mark it paid.
        return paymentService.createPayment(email, request);
    }

    @GetMapping("/order/{orderId}")
    public List<Payment> getPaymentsForOrder(@AuthenticationPrincipal String email,
                                             @PathVariable Long orderId) {
        // Return payments for a specific order after ownership check.
        return paymentService.getPaymentsForOrder(email, orderId);
    }
}
