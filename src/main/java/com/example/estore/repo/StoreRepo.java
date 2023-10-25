package com.example.estore.repo;

import com.example.estore.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {

    public List<Store> findStoreByName(String name);

    @Query(value = "CALL getAllIdStore();", nativeQuery = true)
    public List<Long> getAllId();


    @Query(value = "SELECT get_store_by_id(:id_store)")
    public Optional<Long> getId(@Param("id_store") Long id_store);

    public Store findStoreById(Long id);
}
