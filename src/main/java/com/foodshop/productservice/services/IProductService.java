package com.foodshop.productservice.services;

import com.foodshop.productservice.models.Product;

public interface IProductService {
    Product addProduct(Product product);
    Product updateProduct(Product product,String id);

}
