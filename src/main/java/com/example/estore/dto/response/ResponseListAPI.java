package com.example.estore.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ResponseListAPI <T> (int code, String message, List<String> error, List<?> data){
}
