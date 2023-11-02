package com.example.estore.service;

import com.example.estore.Entity.Product;
import com.example.estore.dto.extract.ResponseSearchProductNameProjectionDTO;
import com.example.estore.dto.request.RequestNewProductDTO;
import com.example.estore.dto.request.SearchDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.dto.response.ResponseProductList;
import org.springframework.validation.Errors;

import java.util.List;

public interface ProductService {

    public ResponseAPI<?> createNewProduct(RequestNewProductDTO productDTO, Errors errors);

//    public List<Product> findProductByName(String name);
//
//    public ResponseAPI<?> searchNameProduct(String names);

    ResponseProductList searchNameProduct(SearchDTO searchDTO, Errors errors);
}
