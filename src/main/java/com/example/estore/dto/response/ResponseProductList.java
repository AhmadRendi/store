package com.example.estore.dto.response;

import com.example.estore.Entity.Product;
import com.example.estore.dto.extract.Coba;
import com.example.estore.dto.extract.ResponseSearchProductNameProjectionDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record ResponseProductList (int code, String message, List<Coba> data, List<String> error){
}
