package lk.ijse.food_ordering_backend.controller;

import lk.ijse.food_ordering_backend.dto.FoodItemDTO;
import lk.ijse.food_ordering_backend.entity.FoodItemStatus;
import lk.ijse.food_ordering_backend.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handles food item API requests.
@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodItemService foodItemService;

    // Save a food item.
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        foodItemService.saveFoodItem(foodItemDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update a food item.
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateFoodItem(@PathVariable Long id, @RequestBody FoodItemDTO foodItemDTO) {
        foodItemService.updateFoodItem(id, foodItemDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete a food item.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Retrieve a food item by id.
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodItemDTO> getFoodItem(@PathVariable Long id) {
        return new ResponseEntity<>(foodItemService.getFoodItem(id), HttpStatus.OK);
    }

    // Retrieve all food items.
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FoodItemDTO>> getAllFoodItems() {
        return new ResponseEntity<>(foodItemService.getAllFoodItems(), HttpStatus.OK);
    }

    // Retrieve food items by category.
    @GetMapping(value = "/category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FoodItemDTO>> getFoodItemsByCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(foodItemService.getFoodItemsByCategory(categoryId), HttpStatus.OK);
    }

    // Retrieve food items by status.
    @GetMapping(value = "/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FoodItemDTO>> getFoodItemsByStatus(@PathVariable FoodItemStatus status) {
        return new ResponseEntity<>(foodItemService.getFoodItemsByStatus(status), HttpStatus.OK);
    }
}