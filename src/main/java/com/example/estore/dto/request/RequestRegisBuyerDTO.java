package com.example.estore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RequestRegisBuyerDTO {


    @Email(message = "email not valid")
    @NotBlank(message = "email can't blank")
    private String email;

    @NotBlank(message = "username can't blank")
    private String username;

    @NotBlank(message = "password can't blank")
    private String password;

    private String roles = "BUYER";

}
