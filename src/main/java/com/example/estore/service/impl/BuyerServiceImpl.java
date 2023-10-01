package com.example.estore.service.impl;

import com.example.estore.Entity.Buyer;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.repo.BuyerRepo;
import com.example.estore.service.BuyerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class BuyerServiceImpl implements BuyerService, UserDetailsService {

    private BuyerRepo buyerRepo;

    private PasswordEncoder passwordEncoder;

    @Override
    public boolean findUsernames(String username) {
        if(buyerRepo.findBuyerByUsernames(username).isPresent()){
            throw new DuplicateKeyException("username telah digunakan pengguna lain");
        }
        return true;
    }

    @Override
    public Buyer findUsername(String username) {
        return buyerRepo.findBuyerByUsernames(username).orElseThrow(() -> new UsernameNotFoundException("username tidak ditemukan"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buyerRepo.findBuyerByUsernames(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    @Override
    public ResponseAPI<?> createAccountsNews() {
        try{
        }catch (
                DuplicateKeyException exception
        ){
            List<String> error = new ArrayList<>();
            error.add(HttpStatus.BAD_REQUEST.name());
            return ResponseAPI.builder()
                    .error(error)
                    .message(exception.getMessage())
                    .code(HttpStatus.BAD_REQUEST.value())
                    .build();
        }catch (
                Throwable exception
        ){
            List<String> error = new ArrayList<>();
            error.add(HttpStatus.INTERNAL_SERVER_ERROR.name());
            return ResponseAPI.builder()
                    .error(error)
                    .message(exception.getMessage())
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
        return null;
    }
}
