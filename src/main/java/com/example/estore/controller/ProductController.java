package com.example.estore.controller;

import com.example.estore.dto.request.RequestNewProductDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
