package com.foodshop.productservice.services;

import com.foodshop.productservice.dto.ProductResponseDTO;
import com.foodshop.productservice.dto.ProductsResponseDTO;
import com.foodshop.productservice.exceptions.ProductNotFoundException;
import com.foodshop.productservice.models.Product;

import java.util.List;

public interface IProductService {
    ProductsResponseDTO getAllProducts(String categoryId);
    ProductResponseDTO getProduct(String id) throws ProductNotFoundException;
    ProductResponseDTO addProduct(Product product);
    ProductResponseDTO updateProduct(Product product,String id) throws ProductNotFoundException;
    ProductResponseDTO deleteProduct(String id) throws ProductNotFoundException;
    ProductsResponseDTO searchProductsWithTitleAndDescription(String text,String categoryId);

}
