package lk.ijse.food_ordering_backend.service.impl;

import lk.ijse.food_ordering_backend.dao.CartDao;
import lk.ijse.food_ordering_backend.dao.CartItemDao;
import lk.ijse.food_ordering_backend.dao.OrderDao;
import lk.ijse.food_ordering_backend.dao.UserDao;
import lk.ijse.food_ordering_backend.dto.OrderDTO;
import lk.ijse.food_ordering_backend.dto.OrderItemDTO;
import lk.ijse.food_ordering_backend.entity.Cart;
import lk.ijse.food_ordering_backend.entity.CartItem;
import lk.ijse.food_ordering_backend.entity.Order;
import lk.ijse.food_ordering_backend.entity.OrderItem;
import lk.ijse.food_ordering_backend.entity.OrderStatus;
import lk.ijse.food_ordering_backend.entity.User;
import lk.ijse.food_ordering_backend.exception.DataNotFoundException;
import lk.ijse.food_ordering_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;

 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Handles order business logic.
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final CartDao cartDao;
    private final CartItemDao cartItemDao;
    private final UserDao userDao;

    // Place an order from the user's cart.
    @Override
    public void placeOrder(Long userId) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + userId));

        Cart cart = cartDao.findByUser_Id(userId)
                .orElseThrow(() -> new DataNotFoundException("Cart not found for user id: " + userId));

        List<CartItem> cartItems = cartItemDao.findByCart_Id(cart.getId());
        if (cartItems.isEmpty()) {
            throw new DataNotFoundException("Cart is empty for user id: " + userId);
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PLACED);
        order.setOrderDate(LocalDateTime.now());

        double totalAmount = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            double itemTotal = cartItem.getFoodItem().getPrice() * cartItem.getQuantity();
            totalAmount += itemTotal;

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setFoodItem(cartItem.getFoodItem());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getFoodItem().getPrice());
            orderItems.add(orderItem);
        }

        order.setTotalAmount(totalAmount);
        order.setOrderItems(orderItems);
        orderDao.save(order);

        cartItemDao.deleteAll(cartItems);
        cart.setCartItems(new ArrayList<>());
        cartDao.save(cart);
    }

    // Retrieve an order by id.
    @Override
    public OrderDTO getOrder(Long id) {
        return mapToDTO(orderDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Order not found with id: " + id)));
    }

    // Retrieve orders by user.
    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {
        return orderDao.findByUser_Id(userId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // Update the status of an order.
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Order not found with id: " + id));

        order.setStatus(status);
        orderDao.save(order);
    }

    // Cancel an order only when it is still placed.
    @Override
    public void cancelOrder(Long id) {
        Order order = orderDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Order not found with id: " + id));

        if (order.getStatus() != OrderStatus.PLACED) {
            throw new DataNotFoundException("Only placed orders can be cancelled");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderDao.save(order);
    }

    // Convert order entity to DTO manually.
    private OrderDTO mapToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderItems(order.getOrderItems() == null ? List.of() : order.getOrderItems().stream().map(this::mapToDTO).toList());
        return orderDTO;
    }

    // Convert order item entity to DTO manually.
    private OrderItemDTO mapToDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setOrderId(orderItem.getOrder().getId());
        orderItemDTO.setFoodItemId(orderItem.getFoodItem().getId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setPrice(orderItem.getPrice());
        return orderItemDTO;
    }
}