package com.foodshop.productservice.dto;

import com.foodshop.productservice.models.Category;
import lombok.Data;

@Data
public class CategoryRequestDTO {

    private String name;

    public Category toCategory(){
        return Category
                .builder()
                .name(name)
                .build();
    }
}
