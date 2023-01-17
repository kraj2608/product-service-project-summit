package com.foodshop.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodshop.productservice.models.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponseDTO {
    private Category category;
    private String message;

    @JsonProperty("status_code")
    private int statusCode;
}
