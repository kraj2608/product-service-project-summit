package com.foodshop.productservice.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException{
    private final String message;

    public CategoryAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
