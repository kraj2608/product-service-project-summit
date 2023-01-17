package com.foodshop.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodshop.productservice.models.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoriesResponseDTO {
    private final List<Category> categories;
    private final String message;

    @JsonProperty("status_code")
    private final int statusCode;
}
