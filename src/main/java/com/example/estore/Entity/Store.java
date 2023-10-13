package com.example.estore.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "tbl_store")
@AllArgsConstructor
@NoArgsConstructor
public class Store {

    @Id
    private Long id;

    private String name;

    private String address;

    private String cellphone;
}
