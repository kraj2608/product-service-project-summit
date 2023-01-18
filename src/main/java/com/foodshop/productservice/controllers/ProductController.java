package com.foodshop.productservice.controllers;

import com.foodshop.productservice.dto.ProductRequestDTO;
import com.foodshop.productservice.dto.ProductsResponseDTO;
import com.foodshop.productservice.models.Product;
import com.foodshop.productservice.services.IProductService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false,value = "category_id")
                                                        String categoryId){
        return new ResponseEntity<>(productService.getAllProducts(categoryId),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id){
        return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ProductsResponseDTO> searchProducts(@RequestParam("search_text") String searchText,
                                                              @RequestParam(required = false,value = "category_id")
                                                              String categoryId){
        return new ResponseEntity<>(productService.searchProductsWithTitleAndDescription(searchText,categoryId),HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO){
        return new ResponseEntity<>(productService.addProduct(productRequestDTO.toProduct()), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> editProduct(@Valid @PathVariable("id") String id,
                                               @RequestBody ProductRequestDTO productRequestDTO){
        return new ResponseEntity<>(productService.updateProduct(productRequestDTO.toProduct(),id),HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String id){
        return new ResponseEntity<>(productService.deleteProduct(id),HttpStatus.OK);
    }


}
