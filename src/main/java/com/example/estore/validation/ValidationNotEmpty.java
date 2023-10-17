package com.example.estore.validation;

public class ValidationNotEmpty extends RuntimeException{

    private static final String message = " can't blank";

    public ValidationNotEmpty(String object){
        super(object + message);
    }
}
