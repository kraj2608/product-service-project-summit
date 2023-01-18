package com.foodshop.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodshop.productservice.models.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {

    private Product product;

    private String message;

    @JsonProperty("status_code")
    private int statusCode;
}
