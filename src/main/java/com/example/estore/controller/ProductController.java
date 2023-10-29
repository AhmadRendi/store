package com.example.estore.controller;

import com.example.estore.Entity.Product;
import com.example.estore.dto.request.RequestNewProductDTO;
import com.example.estore.dto.request.SearchDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.dto.response.ResponseProductList;
import com.example.estore.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {


    private ProductServiceImpl productService;

    @PostMapping("/create")
    public ResponseEntity<ResponseAPI<?>> createNewProduct(@Valid @RequestBody
                                                              RequestNewProductDTO productDTO,
                                                           Errors errors){
        return ResponseEntity.ok(productService.createNewProduct(productDTO, errors));
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseProductList> searchNameProduct(@Valid @RequestBody SearchDTO searchDTO, Errors errors){
        return ResponseEntity.ok(productService.searchNameProduct(searchDTO, errors));
    }
}
