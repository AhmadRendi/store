package com.example.estore.repo;

import com.example.estore.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Long> {

//    @Query(value = "CALL saveOwner(:username,:email,:name,:password,:roles);", nativeQuery = true)
//    public Owner saved(@Param("username") String username,
//                       @Param("email") String email,
//                       @Param("name") String name,
//                       @Param("password") String password,
//                       @Param("roles") String roles);
//
//    @Procedure("saveOwner")
//    public Owner saveOwner(
//            @Param("username") String username,
//            @Param("email") String email,
//            @Param("name") String name,
//            @Param("password") String password,
//            @Param("roles") String roles
//    );


        @Query(value = "SELECT fetchPasswordOwnerByEmail(:email);", nativeQuery = true )
        public String fetchPasswordOwnerByEmail(@Param("email") String email);


    Optional<Owner> findOwnerByEmails(String email);

    Optional<Owner> findOwnerByUsernames(String username);
}
