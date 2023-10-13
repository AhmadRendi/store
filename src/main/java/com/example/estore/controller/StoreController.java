package com.example.estore.controller;

import com.example.estore.dto.request.RequestRegisStoreDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.service.impl.StoreServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

    private StoreServiceImpl service;

    @PostMapping("/create")
    public ResponseEntity<ResponseAPI<?>> createNewStore(@Valid @RequestBody
                                                         RequestRegisStoreDTO regisStoreDTO,
                                                         Errors errors){
        return ResponseEntity.ok(service.createNewStore(regisStoreDTO, errors));
    }

}
