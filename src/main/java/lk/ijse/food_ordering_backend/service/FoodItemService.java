package lk.ijse.food_ordering_backend.service;

import lk.ijse.food_ordering_backend.dto.FoodItemDTO;
import lk.ijse.food_ordering_backend.entity.FoodItemStatus;

import java.util.List;

// Defines food item operations.
public interface FoodItemService {

    // Save a new food item.
    void saveFoodItem(FoodItemDTO foodItemDTO);

    // Update an existing food item.
    void updateFoodItem(Long id, FoodItemDTO foodItemDTO);

    // Delete a food item by id.
    void deleteFoodItem(Long id);

    // Retrieve a food item by id.
    FoodItemDTO getFoodItem(Long id);

    // Retrieve all food items.
    List<FoodItemDTO> getAllFoodItems();

        // Retrieve food items by category id.
        List<FoodItemDTO> getFoodItemsByCategory(Long categoryId);

    // Retrieve food items by status.
        List<FoodItemDTO> getFoodItemsByStatus(FoodItemStatus status);
}