package com.ecommerce.controller;

import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(@Valid @RequestBody Category category){
        // Create a new category record.
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories(){
        // Return all categories for catalog navigation.
        return categoryService.getAllCategories();
    }

}
