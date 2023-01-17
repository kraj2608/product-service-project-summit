package com.foodshop.productservice.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    private final String message;

    public CategoryNotFoundException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
