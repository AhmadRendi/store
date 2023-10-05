package com.example.estore.repo;

import com.example.estore.Entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerRepo extends JpaRepository<Buyer, Long> {

    Optional<Buyer> findBuyerByUsernames(String username);

    Optional<Buyer> findBuyerByEmails(String email);

}