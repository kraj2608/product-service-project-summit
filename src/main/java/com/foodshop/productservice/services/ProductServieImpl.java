package com.foodshop.productservice.services;

import com.foodshop.productservice.constants.ErrorMessages;
import com.foodshop.productservice.exceptions.ProductNotFoundException;
import com.foodshop.productservice.models.Product;
import com.foodshop.productservice.respositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServieImpl implements IProductService{
    private final IProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllByDeleted(false);
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
}
