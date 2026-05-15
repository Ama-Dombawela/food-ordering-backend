package lk.ijse.food_ordering_backend.controller;

import lk.ijse.food_ordering_backend.dto.CartDTO;
import lk.ijse.food_ordering_backend.dto.CartItemDTO;
import lk.ijse.food_ordering_backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// Handles cart API requests.
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // Retrieve a cart by user id.
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getCart(@PathVariable Long userId) {
        CartDTO dto = cartService.getCart(userId);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Cart retrieved successfully.");
        body.put("data", dto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Add an item to the cart.
    @PostMapping(value = "/{userId}/items", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> addItemToCart(@PathVariable Long userId, @RequestBody CartItemDTO cartItemDTO) {
        cartService.addItemToCart(userId, cartItemDTO);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Item added to cart successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    // Remove an item from the cart.
    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<Map<String, Object>> removeItemFromCart(@PathVariable Long userId, @PathVariable Long itemId) {
        cartService.removeItemFromCart(userId, itemId);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Item removed from cart successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Clear the cart for a user.
    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Cart cleared successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}