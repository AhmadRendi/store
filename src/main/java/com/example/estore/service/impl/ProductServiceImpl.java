package com.example.estore.service.impl;

import com.example.estore.Entity.Product;
import com.example.estore.Entity.Store;
import com.example.estore.dto.extract.Coba;
import com.example.estore.dto.extract.ResponseSearchProductNameProjectionDTO;
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
import org.springframework.core.annotation.MergedAnnotations;
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

    private boolean isEmpty(SearchDTO searchDTO){

        if(productRepo.searchProductByNames(searchDTO.getName()).isEmpty()){
            throw new SearchDataNotFoundException("product");
        }else {
            return true;
        }
    }

    private List<Coba> mapperObject(String search){
        List<ResponseSearchProductNameProjectionDTO> list = productRepo.searchProductByNames(search);
        List<Coba> cobaList = new ArrayList<>();
        for(var value : list){
            String name = value.getName();
            Long price = value.getPrice();
            Long id = value.getId();
            String nameStore = value.getNamest();
            String address = value.getAddress();
            Coba coba = new Coba(name, id, price, address, nameStore);
            cobaList.add(coba);
        }
        return cobaList;
    }

    @Override
    public ResponseProductList searchNameProduct(SearchDTO searchDTO, Errors errors) {
        try{
            errorHandling.notBlank("search", searchDTO.getName());
            isEmpty(searchDTO);

            return ResponseProductList
                    .builder()
                    .code(HttpStatus.FOUND.value())
                    .data(mapperObject(searchDTO.getName()))
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
