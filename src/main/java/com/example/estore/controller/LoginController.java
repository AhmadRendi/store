package com.example.estore.controller;

import com.example.estore.dto.request.RequestLogin;
import com.example.estore.dto.request.RequestRegisBuyerDTO;
import com.example.estore.dto.request.RequestRegisOwnerDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.service.impl.BuyerServiceImpl;
import com.example.estore.service.impl.OwnerServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private OwnerServiceImpl ownerService;
    private BuyerServiceImpl buyerService;

    @PostMapping("/registration/owner")
    public ResponseEntity<ResponseAPI<?>> createNewOwner(
            @Valid @RequestBody
            RequestRegisOwnerDTO regisOwnerDTO, Errors errors){

        return ResponseEntity.ok(ownerService.createAccountNews(regisOwnerDTO, errors));
    }

    @PostMapping("/registration/buyer")
    public ResponseEntity<ResponseAPI<?>> createAccountsNew(@Valid @RequestBody RequestRegisBuyerDTO requestRegisBuyerDTO, Errors errors){
        return ResponseEntity.ok(buyerService.createAccountsNews(requestRegisBuyerDTO, errors));
    }

    @GetMapping("/login/buyer")
    public ResponseEntity<ResponseAPI<?>> loginBuyer(@Valid @RequestBody RequestLogin loginBuyer, Errors errors){
        return ResponseEntity.ok(buyerService.login(loginBuyer, errors));
    }

    @GetMapping("/login/owner")
    public ResponseEntity<ResponseAPI<?>> loginOwner(@Valid @RequestBody RequestLogin login, Errors errors){
        return ResponseEntity.ok(ownerService.login(login, errors));
    }

}
