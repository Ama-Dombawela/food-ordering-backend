package lk.ijse.food_ordering_backend.controller;

import lk.ijse.food_ordering_backend.dto.OrderDTO;
import lk.ijse.food_ordering_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Handles order API requests.
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Place an order from the cart.
    @PostMapping(value = "/{userId}")
    public ResponseEntity<Map<String, Object>> placeOrder(@PathVariable Long userId) {
        orderService.placeOrder(userId);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Order placed successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    // Retrieve an order by id.
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getOrder(@PathVariable Long id) {
        OrderDTO dto = orderService.getOrder(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Order retrieved successfully.");
        body.put("data", dto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Retrieve all orders for a user.
    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getOrdersByUser(@PathVariable Long userId) {
        List<OrderDTO> list = orderService.getOrdersByUser(userId);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Orders retrieved successfully.");
        body.put("data", list);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Update an order status.
    @PutMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> updateOrderStatus(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        orderService.updateOrderStatus(id, orderDTO.getStatus());
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Order status updated successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Cancel an order.
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Order cancelled successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}