package lk.ijse.food_ordering_backend.controller;

import lk.ijse.food_ordering_backend.dto.PaymentDTO;
import lk.ijse.food_ordering_backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
// Handles payment API requests.
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // Create a payment for an order.
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> createPayment(@RequestBody PaymentDTO paymentDTO) {
        paymentService.createPayment(paymentDTO);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Payment created successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    // Retrieve a payment by id.
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getPayment(@PathVariable Long id) {
        PaymentDTO dto = paymentService.getPayment(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Payment retrieved successfully.");
        body.put("data", dto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Retrieve a payment by order id.
    @GetMapping(value = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getPaymentByOrder(@PathVariable Long orderId) {
        PaymentDTO dto = paymentService.getPaymentByOrder(orderId);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Payment for order retrieved successfully.");
        body.put("data", dto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Update payment status.
    @PutMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> updatePaymentStatus(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        paymentService.updatePaymentStatus(id, paymentDTO.getStatus());
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Payment status updated successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}