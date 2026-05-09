package lk.ijse.food_ordering_backend.service.impl;

import lk.ijse.food_ordering_backend.dao.CartDao;
import lk.ijse.food_ordering_backend.dao.CartItemDao;
import lk.ijse.food_ordering_backend.dao.FoodItemDao;
import lk.ijse.food_ordering_backend.dao.UserDao;
import lk.ijse.food_ordering_backend.dto.CartDTO;
import lk.ijse.food_ordering_backend.dto.CartItemDTO;
import lk.ijse.food_ordering_backend.entity.Cart;
import lk.ijse.food_ordering_backend.entity.CartItem;
import lk.ijse.food_ordering_backend.entity.FoodItem;
import lk.ijse.food_ordering_backend.entity.FoodItemStatus;
import lk.ijse.food_ordering_backend.entity.User;
import lk.ijse.food_ordering_backend.exception.DataNotFoundException;
import lk.ijse.food_ordering_backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// Handles cart business logic.
@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;
    private final CartItemDao cartItemDao;
    private final UserDao userDao;
    private final FoodItemDao foodItemDao;

    // Retrieve the user's cart, creating one if necessary.
    @Override
    public CartDTO getCart(Long userId) {
        Cart cart = getOrCreateCart(userId);
        return mapToDTO(cart);
    }

    // Add an item to the cart, increasing quantity when the item already exists.
    @Override
    public void addItemToCart(Long userId, CartItemDTO cartItemDTO) {
        Cart cart = getOrCreateCart(userId);
        FoodItem foodItem = foodItemDao.findById(cartItemDTO.getFoodItemId())
                .orElseThrow(() -> new DataNotFoundException("Food item not found with id: " + cartItemDTO.getFoodItemId()));

        if (foodItem.getStatus() != FoodItemStatus.AVAILABLE) {
            throw new DataNotFoundException("Food item is not available with id: " + cartItemDTO.getFoodItemId());
        }

        int quantity = cartItemDTO.getQuantity() == null || cartItemDTO.getQuantity() < 1 ? 1 : cartItemDTO.getQuantity();
        List<CartItem> cartItems = cartItemDao.findByCart_Id(cart.getId());

        CartItem existingItem = cartItems.stream()
                .filter(cartItem -> cartItem.getFoodItem().getId().equals(foodItem.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemDao.save(existingItem);
            return;
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setFoodItem(foodItem);
        cartItem.setQuantity(quantity);
        cartItemDao.save(cartItem);

        cartItems.add(cartItem);
        cart.setCartItems(cartItems);
        cartDao.save(cart);
    }

    // Remove an item from the cart.
    @Override
    public void removeItemFromCart(Long userId, Long itemId) {
        Cart cart = cartDao.findByUser_Id(userId)
                .orElseThrow(() -> new DataNotFoundException("Cart not found for user id: " + userId));

        List<CartItem> cartItems = cartItemDao.findByCart_Id(cart.getId());
        CartItem cartItem = cartItems.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Cart item not found with id: " + itemId));

        cartItemDao.delete(cartItem);
        cartItems.remove(cartItem);
        cart.setCartItems(cartItems);
        cartDao.save(cart);
    }

    // Clear all items from the cart.
    @Override
    public void clearCart(Long userId) {
        Cart cart = cartDao.findByUser_Id(userId)
                .orElseThrow(() -> new DataNotFoundException("Cart not found for user id: " + userId));

        List<CartItem> cartItems = new ArrayList<>(cartItemDao.findByCart_Id(cart.getId()));
        cartItemDao.deleteAll(cartItems);
        cart.setCartItems(new ArrayList<>());
        cartDao.save(cart);
    }

    // Load an existing cart or create a new one for the user.
    private Cart getOrCreateCart(Long userId) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + userId));

        return cartDao.findByUser_Id(userId)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    cart.setCartItems(new ArrayList<>());
                    return cartDao.save(cart);
                });
    }

    // Convert cart entity to DTO manually.
    private CartDTO mapToDTO(Cart cart) {
        List<CartItem> cartItems = cartItemDao.findByCart_Id(cart.getId());
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUser().getId());
        cartDTO.setCartItems(cartItems.stream().map(this::mapToDTO).toList());
        return cartDTO;
    }

    // Convert cart item entity to DTO manually.
    private CartItemDTO mapToDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setCartId(cartItem.getCart().getId());
        cartItemDTO.setFoodItemId(cartItem.getFoodItem().getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        return cartItemDTO;
    }
}