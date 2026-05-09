package lk.ijse.food_ordering_backend.service;

import lk.ijse.food_ordering_backend.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    void saveCategory(CategoryDTO categoryDTO);

    void updateCategory(Long id, CategoryDTO categoryDTO);

    void deleteCategory(Long id);

    CategoryDTO getCategory(Long id);

    List<CategoryDTO> getAllCategories();
}