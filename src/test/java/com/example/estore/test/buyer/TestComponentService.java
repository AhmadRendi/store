package com.example.estore.test.buyer;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;

@SpringBootTest
public class TestComponentService {


    private void checkUsernameIsValid(String username) {
        final byte[] usernames = username.getBytes();
        for (int i = 0; i < username.length(); i++){
            if(usernames[i] == ' '){
                throw new InputMismatchException("username not valid");
            }
        }
    }

    @Test
    void testCheckUsernameIsValid() {
        String username = "ahmad rendi";
        String messageError = null;
        try{
            checkUsernameIsValid(username);
        }catch(
                InputMismatchException exception
        ) {
            messageError = exception.getMessage();
        }
        Assertions.assertEquals(messageError,"username not valid");
    }


}
