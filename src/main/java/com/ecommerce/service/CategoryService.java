package com.ecommerce.service;

import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category){
        // Persist a new category entity.
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        // Return the full category list.
        return categoryRepository.findAll();
    }

}
