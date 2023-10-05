package com.example.estore.validation;

import io.jsonwebtoken.JwtException;

public class TokenExpiredException extends JwtException {

    public TokenExpiredException(String message) {
        super(message);
    }
}
