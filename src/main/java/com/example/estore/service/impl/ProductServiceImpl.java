package com.example.estore.service.impl;

import com.example.estore.Entity.Product;
import com.example.estore.Entity.Store;
import com.example.estore.dto.request.RequestNewProductDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.repo.ProductRepo;
import com.example.estore.service.ProductService;
import com.example.estore.validation.ErrorHandling;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;
    private ErrorHandling errorHandling;
    private StoreServiceImpl storeService;


    private Product productMapper(RequestNewProductDTO productDTO){
        Product product = new Product();

        log.info("store id : " + productDTO.getStore());

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setDescription(productDTO.getDescription());
        Store store = storeService.findById(productDTO.getStore());
        product.setStore(store);
        return product;
    }

    @Override
    public ResponseAPI<?> createNewProduct(RequestNewProductDTO productDTO, Errors errors) {
        try{
            errorHandling.inputMismatchException(errors);
            
            Product product = productMapper(productDTO);

            productRepo.save(product);

            return ResponseAPI.builder()
                    .code(HttpStatus.CREATED.value())
                    .message("added successfully")
                    .data(product)
                    .build();
        }catch (
                InputMismatchException exception
        ){
            List<String> error = new ArrayList<>();

            error.add(HttpStatus.BAD_REQUEST.name());

            return ResponseAPI.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .error(error)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @Override
    public List<Product> findProductByName(String name) {
        return null;
    }
}
