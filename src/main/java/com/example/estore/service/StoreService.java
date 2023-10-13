package com.example.estore.service;

import com.example.estore.Entity.Store;
import com.example.estore.dto.request.RequestRegisStoreDTO;
import com.example.estore.dto.response.ResponseAPI;
import org.springframework.validation.Errors;

import java.util.List;

public interface StoreService {

    List<Store> findNameStore(String name);

    ResponseAPI<?> createNewStore(RequestRegisStoreDTO regisStoreDTO, Errors errors);

}
