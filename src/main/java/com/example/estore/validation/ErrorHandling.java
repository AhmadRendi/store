package com.example.estore.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.InputMismatchException;

@Component
public class ErrorHandling {

    public boolean notBlank(String fieldName, String field){
        if(field.isEmpty()){
            throw new InputMismatchException(fieldName + "can't empty");
        }else if(field.isBlank()){
           throw new InputMismatchException(fieldName + "can't blank");
        }else {
            return true;
        }
    }

    public boolean emailIsValid(String email){
        return false;
    }

    public void inputMismatchException(Errors errors){
        if(errors.hasErrors()){
            for(var error : errors.getAllErrors()){
                throw new InputMismatchException(error.getDefaultMessage());
            }
        }
        return;
    }

    public static boolean inputMissException(Errors errors){
        if(errors.hasErrors()){
            for(var value : errors.getAllErrors()){
                throw new InputMismatchException(value.getDefaultMessage());
            }
        }
        return true;
    }
}
