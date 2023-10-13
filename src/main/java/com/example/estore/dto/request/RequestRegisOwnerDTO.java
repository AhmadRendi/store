package com.example.estore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RequestRegisOwnerDTO {

    @Email(message = "email not valid")
    @NotBlank(message = "email can't blank")
    private String emails;

    @NotBlank(message = "username can't blank")
    private String usernames;

    @NotBlank(message = "password can't blank")
    private String passwords;

    @NotBlank(message = "name can't blank")
    private String names;

    private String role = "OWNER";
}
