package com.example.estore.repo;

import com.example.estore.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query(value = "CALL search_name_product(:names);", nativeQuery = true)
    public List<Product> searchNameProduct(@Param("names") String names);

}
