package com.example.estore.repo;

import com.example.estore.Entity.Product;
import com.example.estore.dto.extract.Coba;
import com.example.estore.dto.response.ResponseProductSearchByName;
import com.example.estore.dto.extract.ResponseSearchProductNameProjectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query(value = "CALL search_name_product(:names);", nativeQuery = true)
    public List<Product> searchNameProduct(@Param("names") String names);

//    @Query(value = "SELECT new com.example.estore.dto.response.ResponseProductSearchByName(" +
//            "e.name," +
//            "e.id," +
//            "e.price," +
//            "e.store) FROM Product e WHERE e.name LIKE CONCAT('%', :names, '%')")
//    public List<ResponseProductSearchByName> searchName(@Param("names") String names);
//
//    @Query("SELECT o.name AS name, o.id AS id, o.price AS price, e.address AS address," +
//            " e.name AS namest FROM Product o JOIN o.store e WHERE o.name LIKE CONCAT('%', :names, '%')")
//    public List<ResponseSearchProductNameProjectionDTO> readName(@Param("names") String names);

    @Query(value = "CALL SearchProducts(:names);", nativeQuery = true)
    public List<ResponseSearchProductNameProjectionDTO> searchProductByNames(@Param("names") String names);

}
