package com.foodshop.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodshop.productservice.models.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductsResponseDTO {
    private List<Product> products;
    private String message;

    @JsonProperty("status_code")
    private int statusCode;
}
