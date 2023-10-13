package com.example.estore.service;

import com.example.estore.Entity.Owner;
import com.example.estore.dto.request.RequestRegisOwnerDTO;
import com.example.estore.dto.response.ResponseAPI;
import org.springframework.security.web.util.OnCommittedResponseWrapper;
import org.springframework.validation.Errors;

import java.util.Optional;

public interface OwnerService {

    public ResponseAPI<?> createAccountNews(RequestRegisOwnerDTO regisOwnerDTO, Errors errors);

}
