package com.example.estore.service.impl;

import com.example.estore.Entity.Buyer;
import com.example.estore.Entity.Role;
import com.example.estore.dto.request.RequestRegisBuyerDTO;
import com.example.estore.dto.request.RequestLoginBuyer;
import com.example.estore.dto.request.RequestUpdateAddressCellphoneBuyer;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.validation.EmailUserNotFoundException;
import com.example.estore.validation.ErrorHandling;
import com.example.estore.extend.UserDetailService;
import com.example.estore.repo.BuyerRepo;
import com.example.estore.security.jwt.JWTService;
import com.example.estore.service.BuyerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Pattern;


@Service
@Slf4j
@AllArgsConstructor
public class BuyerServiceImpl implements BuyerService, UserDetailService {

    private BuyerRepo buyerRepo;

    private PasswordEncoder passwordEncoder;

    private ErrorHandling errorHandling;

    private JWTService service;


    @Override
    public void findUsernames(String username) {
        if(buyerRepo.findBuyerByUsernames(username).isPresent()){
            throw new DuplicateKeyException("username telah digunakan pengguna lain");
        }
        return;
    }

    @Override
    public Buyer findEmail(String email) throws EmailUserNotFoundException {
        return buyerRepo.findBuyerByEmails(email).orElseThrow(() -> new EmailUserNotFoundException("email not found"));
    }

    @Override
    public Buyer findUsername(String username) {
        return buyerRepo.findBuyerByUsernames(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buyerRepo.findBuyerByUsernames(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    @Override
    public UserDetails loadUserByEmails(String email) throws UsernameNotFoundException {
        return buyerRepo.findBuyerByEmails(email).orElseThrow(() -> new UsernameNotFoundException("email not found"));
    }

    private void checkPasswordIsValid(String password){
        boolean findLowerCase = Pattern.compile("[a-z]").matcher(password).find();
        boolean findUpperCase = Pattern.compile("[A-Z]").matcher(password).find();
        boolean findNumber = Pattern.compile("[\\d]").matcher(password).find();
        boolean findSymbol = Pattern.compile("[\\W]").matcher(password).find();
        if(findLowerCase && findUpperCase && findNumber && findSymbol){
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


    private void validationEmailIsReady(String email){
        if(buyerRepo.findBuyerByEmails(email).isPresent()){
            throw new DuplicateKeyException("Email is already");
        }
        return;
    }

    private Buyer mapperToBuyer(RequestRegisBuyerDTO requestRegisBuyerDTO){
        Buyer buyer = new Buyer();
        buyer.setEmails(requestRegisBuyerDTO.getEmail());
        buyer.setUsernames(requestRegisBuyerDTO.getUsername());
        buyer.setPasswords(passwordEncoder.encode(requestRegisBuyerDTO.getPassword()));
        buyer.setRoles(Role.valueOf(requestRegisBuyerDTO.getRoles()));
        return buyer;
    }

    @Override
    public ResponseAPI<?> createAccountsNews(RequestRegisBuyerDTO requestRegisBuyerDTO, Errors errors) {
        try{
            errorHandling.inputMismatchException(errors);
            findUsernames(requestRegisBuyerDTO.getUsername());
            validationEmailIsReady(requestRegisBuyerDTO.getEmail());
            checkUsernameIsValid(requestRegisBuyerDTO.getUsername());
            checkPasswordIsValid(requestRegisBuyerDTO.getPassword());
            checkUsernameIsValid(requestRegisBuyerDTO.getUsername());
            checkPasswordIsValid(requestRegisBuyerDTO.getPassword());

            Buyer buyer = mapperToBuyer(requestRegisBuyerDTO);
            buyerRepo.save(buyer);
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
            error.add(HttpStatus.INTERNAL_SERVER_ERROR.name());
            return ResponseAPI.builder()
                    .error(error)
                    .message(exception.getMessage())
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }

    @Override
    public ResponseAPI<?> login(RequestLoginBuyer loginBuyer, Errors errors) {

        try {

            errorHandling.inputMismatchException(errors);

            var username = service.generatedToken(loadUserByEmails(loginBuyer.getEmail()));
            Buyer buyer = findEmail(loginBuyer.getEmail());
            return ResponseAPI.builder()
                    .token(username)
                    .data(buyer)
                    .code(HttpStatus.FOUND.value())
                    .build();

        } catch (
                UsernameNotFoundException |
                        InputMismatchException
                        exception
        ) {
            List<String> error = new ArrayList<>();
            error.add(HttpStatus.NOT_FOUND.name());
            return ResponseAPI.builder()
                    .message(exception.getMessage())
                    .error(error)
                    .code(HttpStatus.NOT_FOUND.value())
                    .build();
        }
    }


    @Override
    public ResponseAPI<?> UpdateAddressAndCellphones(RequestUpdateAddressCellphoneBuyer buyerUpdate,
                                                     Errors errors) {
        try{
            errorHandling.inputMismatchException(errors);

            buyerRepo.updateAddressAndCellphone(
                    buyerUpdate.getAddress(),
                    buyerUpdate.getCellphone(),
                    buyerUpdate.getId());

            return ResponseAPI.builder()
                    .message("Berhasil Update")
                    .code(HttpStatus.OK.value())
                    .build();
        }catch (
                InputMismatchException exception
        ){
            List<String> error = new ArrayList<>();
            error.add(HttpStatus.BAD_REQUEST.name());

            return ResponseAPI.builder()
                    .message(exception.getMessage())
                    .code(HttpStatus.BAD_REQUEST.value())
                    .error(error)
                    .build();
        }
    }
}
