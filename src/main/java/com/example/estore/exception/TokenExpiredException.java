package com.example.estore.exception;

import io.jsonwebtoken.JwtException;

public class TokenExpiredException extends JwtException {

    public TokenExpiredException(String message) {
        super(message);
    }
}
