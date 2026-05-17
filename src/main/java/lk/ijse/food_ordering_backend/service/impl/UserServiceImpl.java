package lk.ijse.food_ordering_backend.service.impl;

import lk.ijse.food_ordering_backend.dao.CartDao;
import lk.ijse.food_ordering_backend.dao.CartItemDao;
import lk.ijse.food_ordering_backend.dao.OrderDao;
import lk.ijse.food_ordering_backend.dao.OrderItemDao;
import lk.ijse.food_ordering_backend.dao.PaymentDao;
import lk.ijse.food_ordering_backend.dao.UserDao;
import lk.ijse.food_ordering_backend.dto.UserDTO;
import lk.ijse.food_ordering_backend.entity.Order;
import lk.ijse.food_ordering_backend.entity.User;
import lk.ijse.food_ordering_backend.exception.DataNotFoundException;
import lk.ijse.food_ordering_backend.exception.DuplicateEntryException;
import lk.ijse.food_ordering_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Handles user business logic.
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final CartDao cartDao;
    private final CartItemDao cartItemDao;
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final PaymentDao paymentDao;

    // Retrieve all users.
    @Override
    public List<UserDTO> getAllUsers() {
        return userDao.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // Retrieve a user by id.
    @Override
    public UserDTO getUser(Long id) {
        return mapToDTO(userDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + id)));
    }

    // Update a user while preserving password.
    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + id));

        if (userDTO.getEmail() != null && !userDTO.getEmail().equalsIgnoreCase(user.getEmail())
                && userDao.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateEntryException("Email already exists: " + userDTO.getEmail());
        }

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getRole() != null) {
            user.setRole(userDTO.getRole());
        }

        userDao.save(user);
    }

    // Delete a user by id.
    @Override
    public void deleteUser(Long id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + id));

        // 1-3: Delete cart items and cart if user has a cart.
        cartDao.findByUser_Id(id).ifPresent(cart -> {
            cartItemDao.deleteByCart_Id(cart.getId());
            cartDao.delete(cart);
        });

        // 4-7: Delete order items, payments, then orders.
        List<Order> orders = orderDao.findByUser_Id(id);
        for (Order order : orders) {
            orderItemDao.deleteByOrder_Id(order.getId());
            paymentDao.deleteByOrder_Id(order.getId());
        }
        orderDao.deleteAll(orders);

        // 8: Delete user.
        userDao.delete(user);
    }

    // Convert user entity to DTO manually.
    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}