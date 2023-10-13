package com.example.estore.validation;

import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

@Component
public class ValidationField {


    public void checkPasswordIsValid(String password){
        boolean findLowerCase = Pattern.compile("[a-z]").matcher(password).find();
        boolean findUpperCase = Pattern.compile("[A-Z]").matcher(password).find();
        boolean findNumber = Pattern.compile("[\\d]").matcher(password).find();
        boolean findSymbol = Pattern.compile("[\\W]").matcher(password).find();
        if(findLowerCase && findUpperCase && findNumber && findSymbol){
            return;
        }
        throw new InputMismatchException("password not valid");
    }

    public void checkUsernameIsValid(String username) {
        final byte[] usernames = username.getBytes();
        for (int i = 0; i < username.length(); i++){
            if(usernames[i] == ' '){
                throw new InputMismatchException("username not valid");
            }
        }
    }
}
