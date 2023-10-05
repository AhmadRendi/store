package com.example.estore.validation;

public class EmailUserNotFoundException extends RuntimeException {

    public EmailUserNotFoundException(String message){
        super(message);
    }
}
