package com.example.estore.dto.request;

import com.example.estore.Entity.Store;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestNewProductDTO {

    @NotBlank(message = "name product can't blank")
    private String name;

    private long price;

    private long stock;

    private String description;

    private Long store;
}
