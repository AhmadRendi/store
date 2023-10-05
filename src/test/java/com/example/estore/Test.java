package com.example.estore;

import com.example.estore.service.impl.BuyerServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.Errors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class Test {


    @Autowired
    BuyerServiceImpl buyerService;

    Errors errors;

    @org.junit.jupiter.api.Test
    void name() {
        String email = "ahmadrendi@gmail.";
//        boolean isValid = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}(.[a-z]{2,3})+$|^$").matcher(email).find();
        boolean isValid=  Pattern.matches(  "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", email);
//        Matcher matcher = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}(.[a-z]{2,3})+$|^$", Pattern.CASE_INSENSITIVE).matcher(email);


        if(isValid){
            System.out.println("email valid");
        }
    }

}
