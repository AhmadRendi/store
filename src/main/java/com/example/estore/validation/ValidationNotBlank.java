package com.example.estore.validation;

import jakarta.validation.Validation;

public class ValidationNotBlank extends RuntimeException{

    private static final String message = " can't blank";

    public ValidationNotBlank(String object){
        super(object + message);
    }
}
