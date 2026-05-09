package lk.ijse.food_ordering_backend.controller;

import lk.ijse.food_ordering_backend.dto.CartDTO;
import lk.ijse.food_ordering_backend.dto.CartItemDTO;
import lk.ijse.food_ordering_backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Handles cart API requests.
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // Retrieve a cart by user id.
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId) {
        return new ResponseEntity<>(cartService.getCart(userId), HttpStatus.OK);
    }

    // Add an item to the cart.
    @PostMapping(value = "/{userId}/items", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addItemToCart(@PathVariable Long userId, @RequestBody CartItemDTO cartItemDTO) {
        cartService.addItemToCart(userId, cartItemDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Remove an item from the cart.
    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long userId, @PathVariable Long itemId) {
        cartService.removeItemFromCart(userId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Clear the cart for a user.
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}