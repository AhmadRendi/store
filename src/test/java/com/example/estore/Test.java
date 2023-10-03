package com.example.estore;

import com.example.estore.dto.request.RegisBuyerDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.service.impl.BuyerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.InputMismatchException;

@SpringBootTest
public class Test {


    @Autowired
    BuyerServiceImpl buyerService;

    Errors errors;





}
