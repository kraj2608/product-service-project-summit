package com.foodshop.productservice.controllers;

import com.foodshop.productservice.dto.ProductRequestDTO;
import com.foodshop.productservice.dto.ProductResponseDTO;
import com.foodshop.productservice.dto.ProductsResponseDTO;
import com.foodshop.productservice.services.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;


    @GetMapping("")
    public ResponseEntity<ProductsResponseDTO> getAllProducts(@RequestParam(required = false, value = "category_id")
                                                              String categoryId,
                                                              @RequestParam(required = false, value = "search")
                                                              String searchText) {
        return new ResponseEntity<>(productService.getAllProducts(categoryId, searchText), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        return new ResponseEntity<>(productService.addProduct(productRequestDTO.toProduct()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable("id") String id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> editProduct(@Valid @PathVariable("id") String id,
                                                          @RequestBody ProductRequestDTO productRequestDTO) {
        return new ResponseEntity<>(productService.updateProduct(productRequestDTO.toProduct(), id), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable("id") String id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }


}
