package lk.ijse.food_ordering_backend.controller;

import lk.ijse.food_ordering_backend.dto.OrderDTO;
import lk.ijse.food_ordering_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handles order API requests.
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Place an order from the cart.
    @PostMapping(value = "/{userId}")
    public ResponseEntity<Void> placeOrder(@PathVariable Long userId) {
        orderService.placeOrder(userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Retrieve an order by id.
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    // Retrieve all orders for a user.
    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getOrdersByUser(userId), HttpStatus.OK);
    }

    // Update an order status.
    @PutMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        orderService.updateOrderStatus(id, orderDTO.getStatus());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Cancel an order.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}