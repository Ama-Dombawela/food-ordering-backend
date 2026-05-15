package lk.ijse.food_ordering_backend.controller;

import lk.ijse.food_ordering_backend.dto.CategoryDTO;
import lk.ijse.food_ordering_backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Handles category API requests.
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // Save a category
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.saveCategory(categoryDTO);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Category created successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    // Get selected category
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getCategory(@PathVariable Long id) {
        CategoryDTO dto = categoryService.getCategory(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Category retrieved successfully.");
        body.put("data", dto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Get all categories
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAllCategories() {
        List<CategoryDTO> list = categoryService.getAllCategories();
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Categories retrieved successfully.");
        body.put("data", list);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Category deleted successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Update a category
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Category updated successfully.");
        body.put("data", null);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}