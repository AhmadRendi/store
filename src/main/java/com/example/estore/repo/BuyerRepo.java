package com.example.estore.repo;

import com.example.estore.Entity.Buyer;
import com.example.estore.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BuyerRepo extends JpaRepository<Buyer, Long> {


    Optional<Buyer> findBuyerByUsernames(String username);

    Optional<Buyer> findBuyerByEmails(String email);

    @Modifying
    @Transactional
    @Query(value = "CALL updateAddressAndCellphoneBuyer(:address, :cellphone, :id);", nativeQuery = true)
    void updateAddressAndCellphone(@Param("address") String address,@Param("cellphone") String cellphone,@Param("id") Long id);




}
