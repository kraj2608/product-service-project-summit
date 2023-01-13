package com.foodshop.productservice.dto;

import com.foodshop.productservice.models.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private String title;
    private String description;
    private double price;
    private Long quantity;

    public Product toProduct(){
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        return product;
    }
}
