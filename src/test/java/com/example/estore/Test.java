package com.example.estore;

import com.example.estore.Entity.Owner;
import com.example.estore.Entity.Store;
import com.example.estore.repo.OwnerRepo;
import com.example.estore.repo.StoreRepo;
import com.example.estore.service.impl.BuyerServiceImpl;

import com.example.estore.service.impl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.Errors;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@SpringBootTest
public class Test {

    @Autowired
    OwnerRepo ownerRepo;

    @Autowired
    BuyerServiceImpl buyerService;

    @Autowired
    StoreRepo storeRepo;

    @Autowired
    private StoreServiceImpl service;


    @org.junit.jupiter.api.Test
    void testSearchStoreByName() {
        String name = "";

        service.findNameStore(name);
    }

    private Long checkIdIsAlReady(List<Store> list, long searchId){
        long start = 0L;
        long end =  list.size();

        while(start < end){

            long mid = (start + end) / 2;
            Long midId = list.get(Math.toIntExact(mid)).getId();
            long compare = midId.compareTo(searchId);
            if(compare == 0){
                return midId;
            } else if (compare < 0) {
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return null;
    }
    @org.junit.jupiter.api.Test
    void name1() {
        Long id = 1_000_000_000L;
        Random random = new Random();
        Long ids = random.nextLong(id);

        List<Store> list2 = storeRepo.findAll().parallelStream()
                .sorted(Comparator.comparing(Store::getId))
                .toList();

        checkIdIsAlReady(list2,  12L);

    }

    Errors errors;

    @org.junit.jupiter.api.Test
    void name() {
        String email = "ahmadrendi@gmail.";
//        boolean isValid = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}(.[a-z]{2,3})+$|^$").matcher(email).find();
        boolean isValid=  Pattern.matches(  "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", email);
//        Matcher matcher = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}(.[a-z]{2,3})+$|^$", Pattern.CASE_INSENSITIVE).matcher(email);


        if(isValid){
            System.out.println("email valid");
        }
    }

    @org.junit.jupiter.api.Test
    void s() {

        String names = "Ahmad Rendi";
        String password = "123";
        String username = "43";
        String email = "ahmad@gmail.com";
        String role = "OWNER";
//        ownerRepo.saved(username, email, names, password, role);
//        ownerRepo.saveOwner(username, email, names, password, role);
    }
}
