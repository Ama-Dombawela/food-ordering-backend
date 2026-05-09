package lk.ijse.food_ordering_backend.service;

import lk.ijse.food_ordering_backend.dto.CartDTO;
import lk.ijse.food_ordering_backend.dto.CartItemDTO;

// Defines cart operations.
public interface CartService {

    // Retrieve a cart by user id.
    CartDTO getCart(Long userId);

    // Add an item to the user's cart.
    void addItemToCart(Long userId, CartItemDTO cartItemDTO);

    // Remove an item from the user's cart.
    void removeItemFromCart(Long userId, Long itemId);

    // Clear the user's cart.
    void clearCart(Long userId);
}