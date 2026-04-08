package com.ecommerce.dto;

import java.math.BigDecimal;

public class ReportResponse {

    // Total number of orders in the system.
    private long totalOrders;
    // Total number of payments recorded.
    private long totalPayments;
    // Total revenue summed from payments.
    private BigDecimal totalRevenue;

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(long totalPayments) {
        this.totalPayments = totalPayments;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
