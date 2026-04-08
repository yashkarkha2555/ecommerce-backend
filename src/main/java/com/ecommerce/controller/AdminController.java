package com.ecommerce.controller;

import com.ecommerce.dto.UpdateOrderStatusRequest;
import com.ecommerce.dto.UpdateUserRoleRequest;
import com.ecommerce.dto.ReportResponse;
import com.ecommerce.dto.UpdateStockRequest;
import com.ecommerce.model.Order;
import com.ecommerce.model.Payment;
import com.ecommerce.model.User;
import com.ecommerce.service.AdminService;
import com.ecommerce.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final InventoryService inventoryService;

    public AdminController(AdminService adminService, InventoryService inventoryService) {
        this.adminService = adminService;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        // Admin-only list of all users.
        return adminService.getAllUsers();
    }

    @PutMapping("/users/{id}/role")
    public User updateUserRole(@PathVariable Long id,
                               @Valid @RequestBody UpdateUserRoleRequest request) {
        // Update a user's role (USER/ADMIN).
        return adminService.updateUserRole(id, request);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        // Admin-only list of all orders.
        return adminService.getAllOrders();
    }

    @PutMapping("/orders/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id,
                                   @Valid @RequestBody UpdateOrderStatusRequest request) {
        // Update order lifecycle status.
        return adminService.updateOrderStatus(id, request);
    }

    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        // Admin-only list of all payments.
        return adminService.getAllPayments();
    }

    @GetMapping("/reports/summary")
    public ReportResponse getSummaryReport() {
        // Aggregated totals for admin dashboards.
        return adminService.getReport();
    }

    @PutMapping("/inventory/{productId}")
    public void updateInventory(@PathVariable Long productId,
                                @Valid @RequestBody UpdateStockRequest request) {
        // Direct inventory adjustment for admins.
        inventoryService.updateStock(productId, request.getStockQuantity());
    }
}
