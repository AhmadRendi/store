package com.example.estore.service;

import com.example.estore.Entity.Product;
import com.example.estore.dto.request.RequestNewProductDTO;
import com.example.estore.dto.response.ResponseAPI;
import org.springframework.validation.Errors;

import java.util.List;

public interface ProductService {

    public ResponseAPI<?> createNewProduct(RequestNewProductDTO productDTO, Errors errors);

    public List<Product> findProductByName(String name);
}
