package com.foodshop.productservice.services;

import com.foodshop.productservice.constants.ErrorMessages;
import com.foodshop.productservice.constants.SuccessMessages;
import com.foodshop.productservice.dto.ProductResponseDTO;
import com.foodshop.productservice.dto.ProductsResponseDTO;
import com.foodshop.productservice.exceptions.ProductNotFoundException;
import com.foodshop.productservice.models.Category;
import com.foodshop.productservice.models.Product;
import com.foodshop.productservice.respositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{
    private final IProductRepository productRepository;

    private final ICategoryService categoryService;


    @Override
    public ProductsResponseDTO getAllProducts(String categoryId, String searchText) {
        if (categoryId == null && searchText == null){
            return ProductsResponseDTO
                    .builder()
                    .products(productRepository.findAllByDeleted(false))
                    .statusCode(HttpStatus.OK.value())
                    .message(SuccessMessages.PRODUCTS_FETCHED_SUCCESS)
                    .build();
        } else if (categoryId == null){
            return searchProductsForText(searchText);
        } else if (searchText == null){
            List<Category> categories = new ArrayList<>();
            categories.add(categoryService.getCategoryById(categoryId));
            return ProductsResponseDTO
                    .builder()
                    .products(productRepository.findAllByCategoriesContainingAndDeleted(categories,false))
                    .statusCode(HttpStatus.OK.value())
                    .message(SuccessMessages.PRODUCTS_FETCHED_SUCCESS)
                    .build();
        }
        return searchProductsForTextAndCategory(searchText,categoryId);

    }

    @Override
    public ProductResponseDTO getProduct(String id) throws ProductNotFoundException {
        if (productRepository.getProductByIdAndDeleted(id,false) == null){
            throw new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND);
        }
        return ProductResponseDTO
                .builder()
                .product(productRepository.getProductByIdAndDeleted(id,false))
                .message(SuccessMessages.PRODUCT_FETCHED_SUCCESS)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public ProductResponseDTO addProduct(Product product) {
        product.getCategories().forEach(category -> categoryService.checkCategoryNotExistsById(category.getId()));
        return ProductResponseDTO
                .builder()
                .product(productRepository.save(product))
                .message(SuccessMessages.PRODUCT_CREATED_SUCCESSFULLY)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public ProductResponseDTO updateProduct(Product updateProduct,String id) throws ProductNotFoundException {
        Product product = productRepository.getProductByIdAndDeleted(id,false);
        if(product == null){
            throw new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND);
        }
        updateProduct.setId(id);
        return ProductResponseDTO
                .builder()
                .product(productRepository.save(updateProduct))
                .message(SuccessMessages.PRODUCT_UPDATED_SUCCESSFULLY)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public ProductResponseDTO deleteProduct(String id) {
        Product product = productRepository.getProductByIdAndDeleted(id,false);
        if(product == null){
            throw new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND);
        }
        product.setDeleted(true);
        return ProductResponseDTO
                .builder()
                .product(productRepository.save(product))
                .statusCode(HttpStatus.CREATED.value())
                .message(SuccessMessages.PRODUCT_DELETED_SUCCESSFULLY)
                .build();
    }

    private ProductsResponseDTO searchProductsForText(String text){

        return ProductsResponseDTO
                .builder()
                .products(productRepository.searchProductByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndDeleted(text,text,false))
                .message(SuccessMessages.PRODUCT_FETCHED_SUCCESS)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private ProductsResponseDTO searchProductsForTextAndCategory(String text,String categoryId){
        List<Category> categories = new ArrayList<>();
        categories.add(categoryService.getCategoryById(categoryId));

        return ProductsResponseDTO
                .builder()
                .products(productRepository
                        .searchProductByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndCategoriesContainingAndDeleted(
                                text,text,categories,false))
                .statusCode(HttpStatus.OK.value())
                .message(SuccessMessages.PRODUCTS_FETCHED_SUCCESS)
                .build();
    }


}
