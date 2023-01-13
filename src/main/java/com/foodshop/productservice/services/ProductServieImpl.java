package com.foodshop.productservice.services;

import com.foodshop.productservice.models.Product;
import com.foodshop.productservice.respositories.IProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServieImpl implements IProductService{
    private final IProductRepository productRepository;

    public ProductServieImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);

    }
}
