package com.example.estore.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record   ResponseAPI <T> (int code, String message, List<String> error, T data, String token){
}
