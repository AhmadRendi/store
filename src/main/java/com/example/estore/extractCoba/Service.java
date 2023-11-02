package com.example.estore.extractCoba;

import com.example.estore.dto.extract.Coba;
import com.example.estore.dto.extract.ResponseSearchProductNameProjectionDTO;
import com.example.estore.dto.request.SearchDTO;
import com.example.estore.dto.response.ResponseProductList;
import com.example.estore.dto.response.ResponseProductSearchByName;
import com.example.estore.repo.ProductRepo;
import com.example.estore.validation.SearchDataNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service {


    ProductRepo productRepo;

//    public List<Coba> search(String name){
//        return productRepo.searchProductByName(name);
//    }

    public List<ResponseSearchProductNameProjectionDTO> findName(String name){
        return productRepo.searchProductByNames(name);
    }
}
