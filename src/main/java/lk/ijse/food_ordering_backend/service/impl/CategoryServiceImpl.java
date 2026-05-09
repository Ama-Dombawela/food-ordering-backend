package lk.ijse.food_ordering_backend.service.impl;

import lk.ijse.food_ordering_backend.dao.CategoryDao;
import lk.ijse.food_ordering_backend.dto.CategoryDTO;
import lk.ijse.food_ordering_backend.entity.Category;
import lk.ijse.food_ordering_backend.exception.DataNotFoundException;
import lk.ijse.food_ordering_backend.exception.DuplicateEntryException;
import lk.ijse.food_ordering_backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Handles category business logic.
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    // Save a new category.
    @Override
    public void saveCategory(CategoryDTO categoryDTO) {
        if (categoryDao.existsByName(categoryDTO.getName())) {
            throw new DuplicateEntryException("Category name already exists: " + categoryDTO.getName());
        }

        Category category = new Category();
        category.setName(categoryDTO.getName());

        categoryDao.save(category);
    }

    // Update an existing category.
    @Override
    public void updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category not found with id: " + id));

        if (!category.getName().equalsIgnoreCase(categoryDTO.getName())
                && categoryDao.existsByName(categoryDTO.getName())) {
            throw new DuplicateEntryException("Category name already exists: " + categoryDTO.getName());
        }

        category.setName(categoryDTO.getName());
        categoryDao.save(category);
    }

    // Delete a category by id.
    @Override
    public void deleteCategory(Long id) {
        Category category = categoryDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category not found with id: " + id));

        categoryDao.delete(category);
    }

    // Retrieve a category by id.
    @Override
    public CategoryDTO getCategory(Long id) {
        Category category = categoryDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category not found with id: " + id));

        return mapToDTO(category);
    }

    // Retrieve all categories.
    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryDao.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // Convert category entity to DTO manually.
    private CategoryDTO mapToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
}