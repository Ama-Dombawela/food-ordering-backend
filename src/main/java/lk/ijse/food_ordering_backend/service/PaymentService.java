package lk.ijse.food_ordering_backend.service;

import lk.ijse.food_ordering_backend.dto.PaymentDTO;
import lk.ijse.food_ordering_backend.entity.PaymentStatus;

// Defines payment operations.
public interface PaymentService {

    // Create a payment for an order.
    void createPayment(PaymentDTO paymentDTO);

    // Retrieve a payment by id.
    PaymentDTO getPayment(Long id);

    // Retrieve a payment by order id.
    PaymentDTO getPaymentByOrder(Long orderId);

    // Update a payment status.
    void updatePaymentStatus(Long id, PaymentStatus status);
}