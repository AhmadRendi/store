package com.example.estore.service;

import com.example.estore.Entity.Buyer;
import com.example.estore.dto.request.RegisBuyerDTO;
import com.example.estore.dto.response.ResponseAPI;
import org.springframework.validation.Errors;

import java.util.Optional;

public interface BuyerService {

    Buyer findUsername(String username);

    void findUsernames(String username);

    ResponseAPI<?> createAccountsNews(RegisBuyerDTO regisBuyerDTO, Errors errors);
}
