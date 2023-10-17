package com.example.estore.service;

import com.example.estore.Entity.Buyer;
import com.example.estore.dto.request.RequestRegisBuyerDTO;
import com.example.estore.dto.request.RequestLogin;
import com.example.estore.dto.request.RequestUpdateAddressCellphoneBuyer;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.validation.EmailUserNotFoundException;
import org.springframework.validation.Errors;


public interface BuyerService {

    Buyer findUsername(String username);

    void findUsernames(String username);

    ResponseAPI<?> createAccountsNews(RequestRegisBuyerDTO requestRegisBuyerDTO, Errors errors);

    ResponseAPI<?> login(RequestLogin loginBuyer, Errors errors);

    Buyer findEmail(String email) throws EmailUserNotFoundException;

    ResponseAPI<?> UpdateAddressAndCellphones(RequestUpdateAddressCellphoneBuyer addressCellphoneBuyer,
                                              Errors errors
    );

}
