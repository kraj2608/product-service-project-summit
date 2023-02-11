package com.foodshop.productservice.controllers;

import com.foodshop.productservice.dto.CategoriesResponseDTO;
import com.foodshop.productservice.dto.CategoryRequestDTO;
import com.foodshop.productservice.dto.CategoryResponseDTO;
import com.foodshop.productservice.services.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<CategoriesResponseDTO> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CategoryResponseDTO> addCategory(@Valid @RequestBody CategoryRequestDTO category) {
        return new ResponseEntity<>(categoryService.addCategory(category.toCategory()), HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@Valid @RequestBody CategoryRequestDTO categoryDto,
                                                              @PathVariable("categoryId") String categoryID) {
        return new ResponseEntity<>(categoryService.editCategory(categoryID, categoryDto.toCategory()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable("categoryId") String categoryId) {
        return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.CREATED);
    }
}
