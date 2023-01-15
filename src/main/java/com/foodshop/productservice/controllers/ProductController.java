package com.foodshop.productservice.controllers;

import com.foodshop.productservice.dto.ProductDTO;
import com.foodshop.productservice.models.Product;
import com.foodshop.productservice.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id){
        return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.addProduct(productDTO.toProduct()), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable("id") String id,
                                               @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.updateProduct(productDTO.toProduct(),id),HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String id){
        return new ResponseEntity<>(productService.deleteProduct(id),HttpStatus.OK);
    }


}
