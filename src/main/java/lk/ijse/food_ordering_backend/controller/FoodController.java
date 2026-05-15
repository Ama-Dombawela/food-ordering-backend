package lk.ijse.food_ordering_backend.controller;

import lk.ijse.food_ordering_backend.dto.FoodItemDTO;
import lk.ijse.food_ordering_backend.entity.FoodItemStatus;
import lk.ijse.food_ordering_backend.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Handles food item API requests.
@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodItemService foodItemService;

    // Save a food item.
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> saveFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        foodItemService.saveFoodItem(foodItemDTO);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Food item created successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    // Update a food item.
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> updateFoodItem(@PathVariable Long id, @RequestBody FoodItemDTO foodItemDTO) {
        foodItemService.updateFoodItem(id, foodItemDTO);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Food item updated successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Delete a food item.
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Food item deleted successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Retrieve a food item by id.
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getFoodItem(@PathVariable Long id) {
        FoodItemDTO dto = foodItemService.getFoodItem(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Food item retrieved successfully.");
        body.put("data", dto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Retrieve all food items.
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAllFoodItems() {
        List<FoodItemDTO> list = foodItemService.getAllFoodItems();
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Food items retrieved successfully.");
        body.put("data", list);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Retrieve food items by category.
    @GetMapping(value = "/category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getFoodItemsByCategory(@PathVariable Long categoryId) {
        List<FoodItemDTO> list = foodItemService.getFoodItemsByCategory(categoryId);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Food items for the category retrieved successfully.");
        body.put("data", list);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Retrieve food items by status.
    @GetMapping(value = "/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getFoodItemsByStatus(@PathVariable FoodItemStatus status) {
        List<FoodItemDTO> list = foodItemService.getFoodItemsByStatus(status);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Food items by status retrieved successfully.");
        body.put("data", list);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}