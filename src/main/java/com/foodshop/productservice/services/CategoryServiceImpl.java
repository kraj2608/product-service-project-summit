package com.foodshop.productservice.services;

import com.foodshop.productservice.constants.SuccessMessages;
import com.foodshop.productservice.dto.CategoriesResponseDTO;
import com.foodshop.productservice.dto.CategoryResponseDTO;
import com.foodshop.productservice.exceptions.CategoryAlreadyExistsException;
import com.foodshop.productservice.exceptions.CategoryNotFoundException;
import com.foodshop.productservice.models.Category;
import com.foodshop.productservice.respositories.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService{

    private final ICategoryRepository categoryRepository;

    private void checkCategoryExistsByName(String name){
        if (categoryRepository.existsByName(name)){
            throw new CategoryAlreadyExistsException(name+ " category already Exists");
        }
    }

    @Override
    public void checkCategoryNotExistsById(String id){
        if (!categoryRepository.existsById(id)){
            throw new CategoryNotFoundException("Category not found");
        }
    }

    @Override
    public Category getCategoryById(String id){
        checkCategoryNotExistsById(id);
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public CategoryResponseDTO addCategory(Category category) throws CategoryAlreadyExistsException {
        checkCategoryExistsByName(category.getName());
        return CategoryResponseDTO
                .builder()
                .category(categoryRepository.save(category))
                .statusCode(HttpStatus.CREATED.value())
                .message(SuccessMessages.CATEGORY_CREATED_SUCCESS)
                .build();
    }

    @Override
    public CategoryResponseDTO editCategory(String categoryId, Category newCategory) throws CategoryNotFoundException {
        checkCategoryNotExistsById(categoryId);
        Category category = categoryRepository.getCategoryById(categoryId);
        category.setName(newCategory.getName());
        return CategoryResponseDTO
                .builder()
                .category(categoryRepository.save(category))
                .statusCode(HttpStatus.CREATED.value())
                .message(SuccessMessages.CATEGORY_UPDATED_SUCCESS)
                .build();
    }

    @Override
    public CategoryResponseDTO deleteCategory(String categoryId) throws CategoryNotFoundException {
        checkCategoryNotExistsById(categoryId);
        return CategoryResponseDTO
                .builder()
                .category(categoryRepository.deleteCategoryById(categoryId))
                .message(SuccessMessages.CATEGORY_DELETED_SUCCESS)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public CategoriesResponseDTO getAllCategories() {
        return CategoriesResponseDTO
                .builder()
                .categories(categoryRepository.findAll())
                .message(SuccessMessages.CATEGORY_FETCHED_SUCCESS)
                .statusCode(HttpStatus.OK.value())
                .build();
    }


}
