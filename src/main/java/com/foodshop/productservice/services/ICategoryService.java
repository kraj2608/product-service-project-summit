package com.foodshop.productservice.services;

import com.foodshop.productservice.dto.CategoriesResponseDTO;
import com.foodshop.productservice.dto.CategoryResponseDTO;
import com.foodshop.productservice.exceptions.CategoryAlreadyExistsException;
import com.foodshop.productservice.exceptions.CategoryNotFoundException;
import com.foodshop.productservice.models.Category;

public interface ICategoryService {
    CategoryResponseDTO addCategory(Category category) throws CategoryAlreadyExistsException;
    CategoryResponseDTO editCategory(String categoryId,Category category) throws CategoryNotFoundException;

    CategoryResponseDTO deleteCategory(String categoryId) throws CategoryNotFoundException;

    CategoriesResponseDTO getAllCategories();

    void checkCategoryNotExistsById(String id) throws CategoryNotFoundException;

    Category getCategoryById(String id);
}
