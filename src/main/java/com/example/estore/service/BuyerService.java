package com.example.estore.service;

import com.example.estore.Entity.Buyer;
import com.example.estore.dto.response.ResponseAPI;

import java.util.Optional;

public interface BuyerService {

    Buyer findUsername(String username);

    boolean findUsernames(String username);

    ResponseAPI<?> createAccountsNews();
}
