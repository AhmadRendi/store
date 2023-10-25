package com.example.estore.service;

import com.example.estore.Entity.Store;
import com.example.estore.dto.request.RequestRegisStoreDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.dto.response.ResponseListAPI;
import org.springframework.validation.Errors;

import java.util.Optional;

public interface StoreService {

    ResponseListAPI<?> findNameStore(String name);

    ResponseAPI<?> createNewStore(RequestRegisStoreDTO regisStoreDTO, Errors errors);

    public Optional<Long> getId(long id);

    public Store findById(long id);

}
