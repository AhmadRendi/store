package com.example.estore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestRegisStoreDTO {

    private Long id;

    @NotBlank(message = "name can't blank")
    private String name;

    @NotBlank(message = "address can't blank")
    private String address;

    @NotBlank(message = "cellphone can't blank")
    private String cellphone;
}
