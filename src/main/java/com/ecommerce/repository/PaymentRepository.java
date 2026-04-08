package com.ecommerce.repository;

import com.ecommerce.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Retrieve all payments for a given order.
    List<Payment> findByOrderId(Long orderId);

}
