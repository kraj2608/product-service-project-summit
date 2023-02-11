package com.foodshop.productservice.services;

import com.foodshop.productservice.dto.ProductResponseDTO;
import com.foodshop.productservice.dto.ProductsResponseDTO;
import com.foodshop.productservice.exceptions.ProductNotFoundException;
import com.foodshop.productservice.models.Product;


public interface IProductService {
    ProductsResponseDTO getAllProducts(String categoryId,String searchText);
    ProductResponseDTO getProduct(String id) throws ProductNotFoundException;
    ProductResponseDTO addProduct(Product product);
    ProductResponseDTO updateProduct(Product product,String id) throws ProductNotFoundException;
    ProductResponseDTO deleteProduct(String id) throws ProductNotFoundException;

}
