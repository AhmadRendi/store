package com.example.estore.exception;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.InputMismatchException;

@Component
public class ErrorHandling {


    public void inputMismatchException(Errors errors){
        if(errors.hasErrors()){
            for(var error : errors.getAllErrors()){
                throw new InputMismatchException(error.getDefaultMessage());
            }
        }
        return;
    }
}
