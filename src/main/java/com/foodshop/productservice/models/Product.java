package com.foodshop.productservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;


@Document("products")
@Data
@Builder
public class Product {

    @Id
    private String id;
    private String title;
    private String description;
    private Long quantity;
    private double price;
    private double rating;

    @DocumentReference(lazy = true)
    private List<Category> categories;


    private List<String> images;

    @JsonProperty("producer_name")
    private String producerName;

    @JsonProperty("producer_description")
    private String producerDescription;

}
