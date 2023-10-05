package com.example.estore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoginBuyer {

    @NotBlank(message = "email can't blank")
    @Email(message = "email not valid")
    private String email;

    @NotBlank(message = "password can't blank")
    private String password;
}
