package com.foodshop.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodshop.productservice.models.Category;
import com.foodshop.productservice.models.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
public class ProductRequestDTO {

    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Min(0)
    private Double price;

    @NotNull(message = "Quantity is required")
    @Min(0)
    private Long quantity;

    @DocumentReference(lazy = true)
    @NotNull(message = "Categories cannot be empty")
    private List<Category> categories;

    @JsonProperty("producer_name")
    private String producerName;

    @JsonProperty("producer_description")
    private String producerDescription;

    public Product toProduct(){
        return Product
                .builder()
                .title(title)
                .description(description)
                .categories(categories)
                .quantity(quantity)
                .price(price)
                .producerName(producerName)
                .producerDescription(producerDescription)
                .build();
    }
}
