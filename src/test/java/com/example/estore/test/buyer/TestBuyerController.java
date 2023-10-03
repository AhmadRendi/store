package com.example.estore.test.buyer;


import com.example.estore.dto.request.RegisBuyerDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
public class TestBuyerController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void successRegistration() throws Exception {
        RegisBuyerDTO regisBuyerDTO = new RegisBuyerDTO();

        regisBuyerDTO.setEmail("ahmadrendi@gmail.com");
        regisBuyerDTO.setUsername("ahmadrendi");
        regisBuyerDTO.setPassword("@hmAd21");
        regisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regisBuyerDTO))
        ).andExpectAll(
               status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);


                    Assertions.assertNull(responseAPI.error());
                    Assertions.assertNotNull(responseAPI.data());
                    Assertions.assertEquals(201,responseAPI.code() );


                    System.out.println("message : " + responseAPI.message());
                    System.out.println("data : " + responseAPI.data());
                }
        );
    }
}
