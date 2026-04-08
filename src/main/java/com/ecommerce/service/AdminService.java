package com.ecommerce.service;

import com.ecommerce.dto.UpdateOrderStatusRequest;
import com.ecommerce.dto.UpdateUserRoleRequest;
import com.ecommerce.dto.ReportResponse;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderStatus;
import com.ecommerce.model.Payment;
import com.ecommerce.model.User;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.PaymentRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigDecimal;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public AdminService(UserRepository userRepository,
                        OrderRepository orderRepository,
                        PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    public List<User> getAllUsers() {
        // Unfiltered list for admin dashboards.
        return userRepository.findAll();
    }

    public User updateUserRole(Long userId, UpdateUserRoleRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Normalize to uppercase to match stored role values.
        String role = request.getRole().toUpperCase();
        if (!role.equals("USER") && !role.equals("ADMIN")) {
            throw new BadRequestException("Invalid role");
        }
        user.setRole(role);

        return userRepository.save(user);
    }

    public List<Order> getAllOrders() {
        // Unfiltered list for admin reporting.
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        OrderStatus status;
        try {
            // Use enum parsing to validate supported statuses.
            status = OrderStatus.valueOf(request.getStatus().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid order status");
        }
        order.setStatus(status);

        return orderRepository.save(order);
    }

    public List<Payment> getAllPayments() {
        // Unfiltered list for admin reporting.
        return paymentRepository.findAll();
    }

    public ReportResponse getReport() {

        List<Payment> payments = paymentRepository.findAll();

        // Sum all payment amounts to compute revenue.
        BigDecimal totalRevenue = payments.stream()
                .map(Payment::getAmount)
                .filter(amount -> amount != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        ReportResponse report = new ReportResponse();
        report.setTotalOrders(orderRepository.count());
        report.setTotalPayments(payments.size());
        report.setTotalRevenue(totalRevenue);

        return report;
    }
}
