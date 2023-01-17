package com.foodshop.productservice.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("categories")
@Data
@Builder
public class Category {
    @Id
    private String id;

    private String name;
}
