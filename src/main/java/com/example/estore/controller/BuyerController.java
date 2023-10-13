package com.example.estore.controller;

import com.example.estore.dto.request.RequestRegisBuyerDTO;
import com.example.estore.dto.request.RequestLoginBuyer;
import com.example.estore.dto.request.RequestUpdateAddressCellphoneBuyer;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.service.impl.BuyerServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class BuyerController {


    private BuyerServiceImpl buyerService;

    @PutMapping("/update/address/cellphone")
    public ResponseEntity<ResponseAPI<?>> updateAddressAndCellphone(@Valid
                                                                    @RequestBody RequestUpdateAddressCellphoneBuyer addressCellphoneBuyer,
                                                                    Errors errors
    ){
        return ResponseEntity.ok(buyerService.UpdateAddressAndCellphones(addressCellphoneBuyer, errors));
    }


}
