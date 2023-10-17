package com.example.estore.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_product")
public class Product {

    @Id
    private Long id;

    private String name;

    private long stock;

    private long price;

    private String description;
}
