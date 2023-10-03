package com.example.estore.service.impl;

import com.example.estore.Entity.Buyer;
import com.example.estore.Entity.Role;
import com.example.estore.dto.request.RegisBuyerDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.exception.ErrorHandling;
import com.example.estore.repo.BuyerRepo;
import com.example.estore.service.BuyerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Pattern;


@Service
@Slf4j
@AllArgsConstructor
public class BuyerServiceImpl implements BuyerService, UserDetailsService {

    private BuyerRepo buyerRepo;

    private PasswordEncoder passwordEncoder;

    private ErrorHandling errorHandling;


    @Override
    public void findUsernames(String username) {
        if(buyerRepo.findBuyerByUsernames(username).isPresent()){
            throw new DuplicateKeyException("username telah digunakan pengguna lain");
        }
        return;
    }

    @Override
    public Buyer findUsername(String username) {
        return buyerRepo.findBuyerByUsernames(username).orElseThrow(() -> new UsernameNotFoundException("username tidak ditemukan"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buyerRepo.findBuyerByUsernames(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }



    private void checkPasswordIsValid(String password){
        boolean findLowerCaseAndUpperCase = Pattern.compile("[A-Za-z]").matcher(password).find();
        boolean findNumber = Pattern.compile("[\\d]").matcher(password).find();
        boolean findSymbol = Pattern.compile("[\\W]").matcher(password).find();
        if(findLowerCaseAndUpperCase && findNumber && findSymbol){
            return;
        }
        throw new InputMismatchException("password not valid");
    }

    private void checkUsernameIsValid(String username) {
        final byte[] usernames = username.getBytes();
        for (int i = 0; i < username.length(); i++){
            if(usernames[i] == ' '){
                throw new InputMismatchException("username not valid");
            }
        }
    }

    private Buyer mapperToBuyer(RegisBuyerDTO regisBuyerDTO){
        Buyer buyer = new Buyer();
        buyer.setEmails(regisBuyerDTO.getEmail());
        buyer.setUsernames(regisBuyerDTO.getUsername());
        buyer.setPasswords(passwordEncoder.encode(regisBuyerDTO.getPassword()));
        buyer.setRole(Role.valueOf(regisBuyerDTO.getRoles()));
        buyer.setRoles(buyer.getRole());
        return buyer;
    }

    @Override
    public ResponseAPI<?> createAccountsNews(RegisBuyerDTO regisBuyerDTO, Errors errors) {
        try{
            findUsernames(regisBuyerDTO.getUsername());
            errorHandling.inputMismatchException(errors);
            checkUsernameIsValid(regisBuyerDTO.getUsername());
            checkPasswordIsValid(regisBuyerDTO.getPassword());

            Buyer buyer = mapperToBuyer(regisBuyerDTO);

            buyerRepo.save(buyer);

            System.out.println("saya ada disisni ditempat yang tidak error");
            return ResponseAPI.builder()
                    .data(buyer)
                    .message("success")
                    .code(HttpStatus.CREATED.value())
                    .build();

        }catch (
                DuplicateKeyException |
                        UsernameNotFoundException |
                        InputMismatchException
                        exception
        ){
            List<String> error = new ArrayList<>();
            error.add(HttpStatus.BAD_REQUEST.name());
            return ResponseAPI.builder()
                    .error(error)
                    .message(exception.getMessage())
                    .code(HttpStatus.BAD_REQUEST.value())
                    .build();
        }catch (
                Exception
                        exception
        ){
            List<String> error = new ArrayList<>();
            System.out.println("on here");
            System.out.println("saya ada di sini di tempat error");
            error.add(HttpStatus.INTERNAL_SERVER_ERROR.name());
            return ResponseAPI.builder()
                    .error(error)
                    .message(exception.getMessage())
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }
}
