package com.foodshop.productservice.services;

import com.foodshop.productservice.dto.ProductsResponseDTO;
import com.foodshop.productservice.exceptions.ProductNotFoundException;
import com.foodshop.productservice.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts(String categoryId);
    Product getProduct(String id) throws ProductNotFoundException;
    Product addProduct(Product product);
    Product updateProduct(Product product,String id) throws ProductNotFoundException;
    String deleteProduct(String id) throws ProductNotFoundException;
    ProductsResponseDTO searchProductsWithTitleAndDescription(String text,String categoryId);

}
