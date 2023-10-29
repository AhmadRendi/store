package com.example.estore.service.impl;

import com.example.estore.Entity.Product;
import com.example.estore.Entity.Store;
import com.example.estore.dto.request.RequestNewProductDTO;
import com.example.estore.dto.request.SearchDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.dto.response.ResponseProductList;
import com.example.estore.repo.ProductRepo;
import com.example.estore.service.ProductService;
import com.example.estore.validation.ErrorHandling;
import com.example.estore.validation.SearchDataNotFoundException;
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

//    @Override
//    public List<Product> findProductByName(String name) {
//        return null;
//    }

    @Override
    public ResponseProductList searchNameProduct(SearchDTO searchDTO, Errors errors) {
        try{

            errorHandling.notBlank("search", searchDTO.getName());

            return ResponseProductList
                    .builder()
                    .code(HttpStatus.FOUND.value())
                    .data(productRepo.searchNameProduct(searchDTO.getName()))
                    .build();

        }catch (
                SearchDataNotFoundException |
                        InputMismatchException
                        exception
        ){
            List<String> error = new ArrayList<>();
            error.add(exception.getMessage());

            return ResponseProductList
                    .builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(exception.getMessage())
                    .error(error)
                    .build();
        }
    }

//    @Override
//    public ResponseAPI<?> searchNameProduct(String names) {
//        try{
//                return ResponseAPI.builder()
//                        .code(HttpStatus.FOUND.value())
//                        .data(productRepo.searchNameProduct(names))
//                        .build();
//
//        }catch (
//                SearchDataNotFoundException exception
//        ){
//            List<String> error = new ArrayList<>();
//            error.add(HttpStatus.NOT_FOUND.name());
//
//            return ResponseAPI.builder()
//                    .code(HttpStatus.NOT_FOUND.value())
//                    .message("not found")
//                    .error(error)
//                    .build();
//        }
//    }

}
