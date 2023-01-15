package com.foodshop.productservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;


@Document("products")
@Data
public class Product {

    @Id
    private String id;
    private String title;
    private String description;
    private Long quantity;
    private double price;
    private double rating;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean deleted = false;

    private List<String> images;
}
