package com.example.estore.service.impl;

import com.example.estore.Entity.Owner;
import com.example.estore.Entity.Role;
import com.example.estore.dto.request.RequestLogin;
import com.example.estore.dto.request.RequestRegisOwnerDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.extend.UserDetailService;
import com.example.estore.repo.OwnerRepo;
import com.example.estore.security.jwt.JWTService;
import com.example.estore.service.OwnerService;
import com.example.estore.validation.ErrorHandling;
import com.example.estore.validation.ValidationField;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService, UserDetailService {

    private OwnerRepo ownerRepo;
    private PasswordEncoder passwordEncoder;
    private ValidationField validationField;
    private JWTService service;
    private ErrorHandling errorHandling;

    @Override
    public UserDetails loadUserByEmails(String email) throws UsernameNotFoundException {
        return ownerRepo.findOwnerByEmails(email).orElseThrow(() -> new UsernameNotFoundException("email not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ownerRepo.findOwnerByUsernames(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    private void emailIsAlReady(String email){
        if(ownerRepo.findOwnerByEmails(email).isPresent()){
            throw new DuplicateKeyException("email is already");
        }
    }

    private void usernameIsAlReady(String username){
        if(ownerRepo.findOwnerByUsernames(username).isPresent()){
            throw new DuplicateKeyException("username is already");
        }
    }


    private Owner mapperOwner(RequestRegisOwnerDTO regisOwnerDTO){
        Owner owner = new Owner();

        owner.setEmails(regisOwnerDTO.getEmails());
        owner.setUsernames(regisOwnerDTO.getUsernames());
        owner.setPasswords(passwordEncoder.encode(regisOwnerDTO.getPasswords()));
        owner.setNames(regisOwnerDTO.getNames());
        owner.setRole(Role.valueOf(regisOwnerDTO.getRole()));

        return owner;
    }

    @Override
    public ResponseAPI<?> createAccountNews(RequestRegisOwnerDTO regisOwnerDTO, Errors errors) {
        try{
            emailIsAlReady(regisOwnerDTO.getEmails());
            usernameIsAlReady(regisOwnerDTO.getUsernames());
            validationField.checkUsernameIsValid(regisOwnerDTO.getUsernames());
            validationField.checkPasswordIsValid(regisOwnerDTO.getPasswords());

            Owner owner = mapperOwner(regisOwnerDTO);

            ownerRepo.save(owner);

            String token = service.generatedToken(loadUserByEmails(owner.getEmails()));
            return ResponseAPI.builder()
                    .code(HttpStatus.CREATED.value())
                    .data(owner)
                    .message("added successfully")
                    .token(token)
                    .build();

        }catch (
                DuplicateKeyException |
                InputMismatchException exception
        ){
            List<String> error = new ArrayList<>();
            error.add(HttpStatus.BAD_REQUEST.name());

           return  ResponseAPI.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .error(error)
                    .message(exception.getMessage())
                    .build();
        }
    }


    private String fetchPassword(String email){
        return ownerRepo.fetchPasswordOwnerByEmail(email);
    }

    @Override
    public ResponseAPI<?> login(RequestLogin login, Errors errors) {
        try{
            errorHandling.inputMismatchException(errors);
            Owner owner = findOwnerByEmail(login.getEmail());
            var email = loadUserByEmails(login.getEmail());
            validationField.passwordIsTrue(login.getPassword(), fetchPassword(login.getEmail()));

            String token = service.generatedToken(email);
            return ResponseAPI.builder()
                    .code(HttpStatus.OK.value())
                    .token(token)
                    .data(owner)
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
        }catch (
                UsernameNotFoundException | BadCredentialsException exception
        ){

            if(exception instanceof BadCredentialsException){
                List<String> error = new ArrayList<>();

                error.add(HttpStatus.UNAUTHORIZED.name());

                return ResponseAPI.builder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .error(error)
                        .message(exception.getMessage())
                        .build();
            }
            List<String> error = new ArrayList<>();

            error.add(HttpStatus.FORBIDDEN.name());

            return ResponseAPI.builder()
                    .code(HttpStatus.FORBIDDEN.value())
                    .error(error)
                    .message(exception.getMessage())
                    .build();
        }
    }


    @Override
    public Optional<Owner> findOwnerByEmails(String email) {
        return Optional.empty();
    }

    @Override
    public Owner findOwnerByEmail(String email) {
        return ownerRepo.findOwnerByEmails(email).orElseThrow(() -> new UsernameNotFoundException("email not found"));
    }
}
