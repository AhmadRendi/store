package com.example.estore.dto.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RegisPembeliDTO {

    private String username;

    private String password;

    private String noTelp;

    private String alamat;
}
