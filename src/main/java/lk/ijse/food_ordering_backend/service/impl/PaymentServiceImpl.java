package lk.ijse.food_ordering_backend.service.impl;

import lk.ijse.food_ordering_backend.dao.OrderDao;
import lk.ijse.food_ordering_backend.dao.PaymentDao;
import lk.ijse.food_ordering_backend.dto.PaymentDTO;
import lk.ijse.food_ordering_backend.entity.Order;
import lk.ijse.food_ordering_backend.entity.Payment;
import lk.ijse.food_ordering_backend.entity.PaymentStatus;
import lk.ijse.food_ordering_backend.exception.DataNotFoundException;
import lk.ijse.food_ordering_backend.exception.DuplicateEntryException;
import lk.ijse.food_ordering_backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

// Handles payment business logic.
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDao paymentDao;
    private final OrderDao orderDao;

    // Create a payment for an order.
    @Override
    public void createPayment(PaymentDTO paymentDTO) {
        Order order = orderDao.findById(paymentDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("Order not found with id: " + paymentDTO.getOrderId()));

        if (paymentDao.findByOrder_Id(paymentDTO.getOrderId()).isPresent()) {
            throw new DuplicateEntryException("Payment already exists for order id: " + paymentDTO.getOrderId());
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setPaymentDate(LocalDateTime.now());

        paymentDao.save(payment);
    }

    // Retrieve a payment by id.
    @Override
    public PaymentDTO getPayment(Long id) {
        return mapToDTO(paymentDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Payment not found with id: " + id)));
    }

    // Retrieve a payment by order id.
    @Override
    public PaymentDTO getPaymentByOrder(Long orderId) {
        return mapToDTO(paymentDao.findByOrder_Id(orderId)
                .orElseThrow(() -> new DataNotFoundException("Payment not found for order id: " + orderId)));
    }

    // Update payment status.
    @Override
    public void updatePaymentStatus(Long id, PaymentStatus status) {
        Payment payment = paymentDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Payment not found with id: " + id));

        payment.setStatus(status);
        paymentDao.save(payment);
    }

    // Convert payment entity to DTO manually.
    private PaymentDTO mapToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setOrderId(payment.getOrder().getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setStatus(payment.getStatus());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        return paymentDTO;
    }
}