package lk.ijse.food_ordering_backend.service.impl;

import lk.ijse.food_ordering_backend.dao.CategoryDao;
import lk.ijse.food_ordering_backend.dao.FoodItemDao;
import lk.ijse.food_ordering_backend.dto.FoodItemDTO;
import lk.ijse.food_ordering_backend.entity.Category;
import lk.ijse.food_ordering_backend.entity.FoodItem;
import lk.ijse.food_ordering_backend.entity.FoodItemStatus;
import lk.ijse.food_ordering_backend.exception.DataNotFoundException;
import lk.ijse.food_ordering_backend.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Handles food item business logic.
@Service
@Transactional
@RequiredArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemDao foodItemDao;
    private final CategoryDao categoryDao;

    // Save a new food item and default its status to AVAILABLE.
    @Override
    public void saveFoodItem(FoodItemDTO foodItemDTO) {
        Category category = categoryDao.findById(foodItemDTO.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Category not found with id: " + foodItemDTO.getCategoryId()));

        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodItemDTO.getName());
        foodItem.setDescription(foodItemDTO.getDescription());
        foodItem.setPrice(foodItemDTO.getPrice());
        foodItem.setStatus(FoodItemStatus.AVAILABLE);
        foodItem.setCategory(category);

        foodItemDao.save(foodItem);
    }

    // Update an existing food item.
    @Override
    public void updateFoodItem(Long id, FoodItemDTO foodItemDTO) {
        FoodItem foodItem = foodItemDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Food item not found with id: " + id));

        if (foodItemDTO.getCategoryId() != null) {
            Category category = categoryDao.findById(foodItemDTO.getCategoryId())
                    .orElseThrow(() -> new DataNotFoundException("Category not found with id: " + foodItemDTO.getCategoryId()));
            foodItem.setCategory(category);
        }

        foodItem.setName(foodItemDTO.getName());
        foodItem.setDescription(foodItemDTO.getDescription());
        foodItem.setPrice(foodItemDTO.getPrice());
        if (foodItemDTO.getStatus() != null) {
            foodItem.setStatus(foodItemDTO.getStatus());
        }

        foodItemDao.save(foodItem);
    }

    // Delete a food item by id.
    @Override
    public void deleteFoodItem(Long id) {
        FoodItem foodItem = foodItemDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Food item not found with id: " + id));

        foodItemDao.delete(foodItem);
    }

    // Retrieve a food item by id.
    @Override
    public FoodItemDTO getFoodItem(Long id) {
        return mapToDTO(foodItemDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Food item not found with id: " + id)));
    }

    // Retrieve all food items.
    @Override
    public List<FoodItemDTO> getAllFoodItems() {
        return foodItemDao.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // Retrieve food items by category.
    @Override
    public List<FoodItemDTO> getFoodItemsByCategory(Long categoryId) {
        return foodItemDao.findByCategory_Id(categoryId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // Retrieve food items by status.
    @Override
    public List<FoodItemDTO> getFoodItemsByStatus(FoodItemStatus status) {
        return foodItemDao.findByStatus(status)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // Convert food item entity to DTO manually.
    private FoodItemDTO mapToDTO(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        foodItemDTO.setId(foodItem.getId());
        foodItemDTO.setName(foodItem.getName());
        foodItemDTO.setDescription(foodItem.getDescription());
        foodItemDTO.setPrice(foodItem.getPrice());
        foodItemDTO.setStatus(foodItem.getStatus());
        foodItemDTO.setCategoryId(foodItem.getCategory() != null ? foodItem.getCategory().getId() : null);
        return foodItemDTO;
    }
}