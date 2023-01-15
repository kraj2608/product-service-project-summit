package com.foodshop.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
public class ErrorResponseDTO {

    @JsonProperty("status_code")
    private int statusCode;
    private String message;


}
