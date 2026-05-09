package lk.ijse.food_ordering_backend.service;

import lk.ijse.food_ordering_backend.dto.OrderDTO;
import lk.ijse.food_ordering_backend.entity.OrderStatus;

import java.util.List;

// Defines order operations.
public interface OrderService {

    // Place an order from the user's cart.
    void placeOrder(Long userId);

    // Retrieve an order by id.
    OrderDTO getOrder(Long id);

    // Retrieve all orders by user id.
    List<OrderDTO> getOrdersByUser(Long userId);

    // Update an order status.
    void updateOrderStatus(Long id, OrderStatus status);

    // Cancel an order by id.
    void cancelOrder(Long id);
}