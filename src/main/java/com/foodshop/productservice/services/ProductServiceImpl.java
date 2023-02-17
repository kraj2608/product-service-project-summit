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
        if (categoryId != null && searchText != null){
            return searchProductsForTextAndCategory(searchText,categoryId);
        } else if (searchText != null){
            return searchProductsForText(searchText);
        } else if (categoryId != null){
            List<Category> categories = new ArrayList<>();
            categories.add(categoryService.getCategoryById(categoryId));
            return ProductsResponseDTO
                    .builder()
                    .products(productRepository.findAllByCategoriesContaining(categories))
                    .statusCode(HttpStatus.OK.value())
                    .message(SuccessMessages.PRODUCTS_FETCHED_SUCCESS)
                    .build();
        }
        return ProductsResponseDTO
                .builder()
                .products(productRepository.findAll())
                .statusCode(HttpStatus.OK.value())
                .message(SuccessMessages.PRODUCTS_FETCHED_SUCCESS)
                .build();

    }

    @Override
    public ProductResponseDTO getProduct(String id) throws ProductNotFoundException {
        if (productRepository.getProductById(id) == null){
            throw new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND);
        }
        return ProductResponseDTO
                .builder()
                .product(productRepository.getProductById(id))
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
        Product product = productRepository.getProductById(id);
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
        Product product = productRepository.getProductById(id);
        if(product == null){
            throw new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND);
        }
        productRepository.delete(product);
        return ProductResponseDTO
                .builder()
                .product(product)
                .statusCode(HttpStatus.CREATED.value())
                .message(SuccessMessages.PRODUCT_DELETED_SUCCESSFULLY)
                .build();
    }

    private ProductsResponseDTO searchProductsForText(String text){

        return ProductsResponseDTO
                .builder()
                .products(productRepository.searchProductByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(text,text))
                .message(SuccessMessages.PRODUCT_FETCHED_SUCCESS)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private ProductsResponseDTO searchProductsForTextAndCategory(String text,String categoryId){
        List<Category> categories = new ArrayList<>();
        categories.add(categoryService.getCategoryById(categoryId));
        List<Product> products = productRepository.findAllByCategoriesContaining(categories);
        List<Product> searchedProducts = products.stream().filter( item -> item.getTitle().contains(text) || item.getDescription().contains(text)).toList();
        return ProductsResponseDTO
                .builder()
                .products(searchedProducts)
                .statusCode(HttpStatus.OK.value())
                .message(SuccessMessages.PRODUCTS_FETCHED_SUCCESS)
                .build();
    }


}
