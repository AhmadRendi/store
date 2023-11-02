package com.example.estore.validation;

import org.springframework.stereotype.Component;

public class SearchDataNotFoundException extends RuntimeException{

    private static final String messages = " not found";

    public SearchDataNotFoundException(String object) {
        super(object + messages);
    }
}
