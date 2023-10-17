package com.example.estore.repo;

import com.example.estore.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {

    public List<Store> findStoreByName(String name);



}
