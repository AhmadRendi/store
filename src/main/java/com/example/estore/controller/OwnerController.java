package com.example.estore.controller;

import com.example.estore.dto.request.RequestRegisOwnerDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.service.impl.OwnerServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/owner/")
public class OwnerController {

    private OwnerServiceImpl service;

}
