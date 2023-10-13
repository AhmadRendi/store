package com.example.estore.service.impl;

import com.example.estore.Entity.Store;
import com.example.estore.dto.request.RequestRegisStoreDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.repo.StoreRepo;
import com.example.estore.service.StoreService;
import com.example.estore.validation.ErrorHandling;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {

    private StoreRepo storeRepo;

    private ErrorHandling errorHandling;

    private Long checkIdIsAlReady(List<Store> list, long searchId){
        long start = 0L;
        long end = (long) list.size();

        while(start < end){

            long mid = (start + end) / 2;
            Long midId = list.get(Math.toIntExact(mid)).getId();
            long compare = midId.compareTo(searchId);
            if(compare == 0){
                return midId;
            } else if (compare < 0) {
                end = mid;
            }else {
                start = mid;
            }
        }
        return null;
    }

    private Store storeMapper(RequestRegisStoreDTO regisStoreDTO){
        Store store = new Store();
        long range = 1_000_000_000_000L;
        Random random = new Random();
        long id = 0L;
        for(int i = 0; i <= 1; i++){
            id = random.nextLong(range);
            if(checkIdIsAlReady(
                    storeRepo.findAll()
                            .parallelStream()
                            .sorted(Comparator.comparing(
                                    Store::getId
                            ))
                            .toList(), id) == null
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


    @Override
    public List<Store> findNameStore(String name) {
        return null;
    }


}
