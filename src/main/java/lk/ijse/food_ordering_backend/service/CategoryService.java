package lk.ijse.food_ordering_backend.service;

import lk.ijse.food_ordering_backend.dto.CategoryDTO;

import java.util.List;

// Defines category operations.
public interface CategoryService {

    // Save a new category.
    void saveCategory(CategoryDTO categoryDTO);

    // Update an existing category.
    void updateCategory(Long id, CategoryDTO categoryDTO);

    // Delete a category by id.
    void deleteCategory(Long id);

    // Retrieve a category by id.
    CategoryDTO getCategory(Long id);

    // Retrieve all categories.
    List<CategoryDTO> getAllCategories();
}