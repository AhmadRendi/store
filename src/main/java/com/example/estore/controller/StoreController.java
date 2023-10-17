package com.example.estore.controller;

import com.example.estore.dto.request.RequestRegisStoreDTO;
import com.example.estore.dto.request.Search;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.dto.response.ResponseListAPI;
import com.example.estore.service.impl.StoreServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search/name")
    public ResponseEntity<ResponseListAPI<?>> findStoreByName(@RequestBody Search search){
        return ResponseEntity.ok(service.findNameStore(search.getName()));
    }

}
