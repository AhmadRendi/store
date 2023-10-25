package com.example.estore.service.impl;

import com.example.estore.Entity.Store;
import com.example.estore.dto.request.RequestRegisStoreDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.dto.response.ResponseListAPI;
import com.example.estore.repo.StoreRepo;
import com.example.estore.service.StoreService;
import com.example.estore.validation.ErrorHandling;
import com.example.estore.validation.SearchDataNotFoundException;
import com.example.estore.validation.ValidationNotBlank;
import com.example.estore.validation.ValidationNotEmpty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.*;


@Service
@AllArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {

    private StoreRepo storeRepo;

    private ErrorHandling errorHandling;

    private Long checkIdIsAlReady(List<Long> list, long searchId){
        long start = 0L;
        long end = (long) list.size();
        while(start <= end){

            long mid = (start + end) / 2;
            Long midId = list.get(Math.toIntExact(mid));
            long compare = midId.compareTo(searchId);
            if(compare == 0){
                return midId;
            } else if (compare < 0) {
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return null;
    }

    private Store storeMapper(RequestRegisStoreDTO regisStoreDTO){
        Store store = new Store();
        long range = 1_000;
        Random random = new Random();
        long id = 0L;
        for(int i = 0; i <= 1; i++){
            id = random.nextLong(range);
            if(checkIdIsAlReady(
                   storeRepo.getAllId(), id) == null
            ){
                regisStoreDTO.setId(id);
                store.setId(regisStoreDTO.getId());
                store.setName(regisStoreDTO.getName());
                store.setAddress(regisStoreDTO.getAddress());
                store.setCellphone(regisStoreDTO.getCellphone());
            }else {
                i = 0;
            }
        }
        return store;
    }

    @Override
    public ResponseAPI<?> createNewStore(RequestRegisStoreDTO regisStoreDTO, Errors errors) {
        try{
            errorHandling.inputMismatchException(errors);

            Store store = storeMapper(regisStoreDTO);

            storeRepo.save(store);

            log.info(store.getAddress());

            return ResponseAPI.builder()
                    .code(HttpStatus.CREATED.value())
                    .data(store)
                    .message("added successfully")
                    .build();
        }catch (
                InputMismatchException exception
        ){
            List<String> error = new ArrayList<>();
            error.add(HttpStatus.BAD_REQUEST.name());

            return ResponseAPI.builder()
                    .message(exception.getMessage())
                    .error(error)
                    .code(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    private void findName(String name){
        List<Store> list = storeRepo.findStoreByName(name);
        if(list.isEmpty()){
            throw new SearchDataNotFoundException("Store ");
        }else {
            return;
        }
    }

    private void validationSearchNotBlank(String object){
        if(object.isBlank()){
            throw new ValidationNotBlank("search");
        }
        return;
    }

    private void validationSearchNotEmpty(String object){
        if(object.isEmpty()){
            throw new ValidationNotEmpty("search");
        }
        return;
    }

    @Override
    public ResponseListAPI<?> findNameStore(String name) {

        try{
            validationSearchNotBlank(name);
            validationSearchNotBlank(name);
            findName(name);


            List<Store> list = storeRepo.findStoreByName(name);

            return ResponseListAPI
                    .builder()
                    .code(HttpStatus.FOUND.value())
                    .data(list)
                    .build();
        }catch (
                SearchDataNotFoundException  exception
        ){
            List<String> error = new ArrayList<>();
            error.add(HttpStatus.NOT_FOUND.name());

            return ResponseListAPI
                    .builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .error(error)
                    .build();
        }catch (
                ValidationNotEmpty |
                        ValidationNotBlank exception
        ){
            List<String> error = new ArrayList<>();
            error.add(HttpStatus.BAD_REQUEST.name());
            return ResponseListAPI.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .error(error)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @Override
    public Optional<Long> getId(long id) {
        return storeRepo.getId(id);
    }

    @Override
    public Store findById(long id) {
        return storeRepo.findStoreById(id);
    }
}
