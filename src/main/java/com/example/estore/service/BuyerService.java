package com.example.estore.service;

import com.example.estore.Entity.Buyer;
import com.example.estore.dto.request.RequestRegisBuyerDTO;
import com.example.estore.dto.request.RequestLoginBuyer;
import com.example.estore.dto.request.RequestUpdateAddressCellphoneBuyer;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.validation.EmailUserNotFoundException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.Errors;

import java.util.Optional;

public interface BuyerService {

    Buyer findUsername(String username);

    void findUsernames(String username);

    ResponseAPI<?> createAccountsNews(RequestRegisBuyerDTO requestRegisBuyerDTO, Errors errors);


    ResponseAPI<?> login(RequestLoginBuyer loginBuyer, Errors errors);

    Buyer findEmail(String email) throws EmailUserNotFoundException;

    ResponseAPI<?> UpdateAddressAndCellphones(RequestUpdateAddressCellphoneBuyer addressCellphoneBuyer,
                                              Errors errors
    );

}
