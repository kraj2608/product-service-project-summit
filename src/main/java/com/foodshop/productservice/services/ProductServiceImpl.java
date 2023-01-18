package com.foodshop.productservice.services;

import com.foodshop.productservice.constants.ErrorMessages;
import com.foodshop.productservice.constants.SuccessMessages;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{
    private final IProductRepository productRepository;

    private final ICategoryService categoryService;


    @Override
    public List<Product> getAllProducts(String categoryId) {
        if (categoryId == null){
            return productRepository.findAllByDeleted(false);
        }
        List<Category> categories = new ArrayList<>();
        categories.add(categoryService.getCategoryById(categoryId));
        return productRepository.findAllByCategoriesContainingAndDeleted(categories,false);
    }

    @Override
    public Product getProduct(String id) throws ProductNotFoundException {
        if (productRepository.getProductByIdAndDeleted(id,false) == null){
            throw new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND);
        }
        return productRepository.getProductByIdAndDeleted(id,false);
    }

    @Override
    public Product addProduct(Product product) {
        product.getCategories().forEach(category -> categoryService.checkCategoryNotExistsById(category.getId()));
        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Product updateProduct,String id) throws ProductNotFoundException {
        Product product = productRepository.getProductByIdAndDeleted(id,false);
        if(product == null){
            throw new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND);
        }
        updateProduct.setId(id);
        productRepository.delete(product);
        productRepository.save(updateProduct);
        return updateProduct;
    }

    @Override
    public String deleteProduct(String id) {
        Product product = productRepository.getProductByIdAndDeleted(id,false);
        if(product == null){
            throw new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND);
        }
        product.setDeleted(true);
        updateProduct(product,product.getId());
        return "Product deleted Successfully";
    }

    private ProductsResponseDTO searchProductsForText(String text){

        return ProductsResponseDTO
                .builder()
                .products(productRepository.searchProductByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndDeleted(text,text,false))
                .message(SuccessMessages.PRODUCTS_FETCHED_SUCCESS)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private ProductsResponseDTO searchProductsForTextAndCategory(String text,String categoryId){
        List<Product> searchedProducts = productRepository
                .searchProductByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndDeleted(text,text,false);
        List<Product> matchedProducts = new ArrayList<>();
        for (Product product:searchedProducts) {
            List<Category> categories = product.getCategories();
            for (Category category:categories) {
                if(Objects.equals(category.getId(), categoryId)){
                    matchedProducts.add(product);
                    break;
                }
            }
        }

        return ProductsResponseDTO
                .builder()
                .products(matchedProducts)
                .statusCode(HttpStatus.OK.value())
                .message(SuccessMessages.PRODUCTS_FETCHED_SUCCESS)
                .build();
    }

    @Override
    public ProductsResponseDTO searchProductsWithTitleAndDescription(String text,String category) {
        if (category == null){
            return searchProductsForText(text);
        }
        return searchProductsForTextAndCategory(text,category);

    }
}
