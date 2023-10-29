package com.example.estore.dto.response;

import com.example.estore.Entity.Product;
import lombok.Builder;

import java.util.List;

@Builder
public record ResponseProductList (int code, String message, List<Product> data, List<String> error){
}
