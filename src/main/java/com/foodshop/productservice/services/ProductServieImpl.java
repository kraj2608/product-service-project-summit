package com.foodshop.productservice.services;

import com.foodshop.productservice.models.Product;
import com.foodshop.productservice.respositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServieImpl implements IProductService{
    private final IProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Product updateProduct,String id) {
        Product product = productRepository.getProductById(id);
        updateProduct.setId(id);
        productRepository.delete(product);
        productRepository.save(updateProduct);
        return updateProduct;
    }
}
