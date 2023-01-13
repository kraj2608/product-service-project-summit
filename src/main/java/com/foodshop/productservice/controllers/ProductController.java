package com.foodshop.productservice.controllers;

import com.foodshop.productservice.dto.ProductDTO;
import com.foodshop.productservice.models.Product;
import com.foodshop.productservice.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.addProduct(productDTO.toProduct()), HttpStatus.CREATED);
    }
}
