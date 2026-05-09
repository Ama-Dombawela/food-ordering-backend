package lk.ijse.food_ordering_backend.controller;

import lk.ijse.food_ordering_backend.dto.PaymentDTO;
import lk.ijse.food_ordering_backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Handles payment API requests.
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // Create a payment for an order.
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPayment(@RequestBody PaymentDTO paymentDTO) {
        paymentService.createPayment(paymentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Retrieve a payment by id.
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.getPayment(id), HttpStatus.OK);
    }

    // Retrieve a payment by order id.
    @GetMapping(value = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentDTO> getPaymentByOrder(@PathVariable Long orderId) {
        return new ResponseEntity<>(paymentService.getPaymentByOrder(orderId), HttpStatus.OK);
    }

    // Update payment status.
    @PutMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePaymentStatus(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        paymentService.updatePaymentStatus(id, paymentDTO.getStatus());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}