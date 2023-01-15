package com.foodshop.productservice.exceptions;

public class ProductNotFoundException extends RuntimeException{

    private final String message;

    public ProductNotFoundException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}