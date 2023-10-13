package com.example.estore.test.store;

import com.example.estore.dto.request.RequestRegisStoreDTO;
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
public class TestRegisStore {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void regisSuccess() throws Exception{
        RequestRegisStoreDTO regisStoreDTO = new RequestRegisStoreDTO();
        regisStoreDTO.setName("Toko buku");
        regisStoreDTO.setAddress("Jakarta Selatan");
        regisStoreDTO.setCellphone("082209874632");

        mockMvc.perform(
                post("/api/create/store")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regisStoreDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNull(responseAPI.error());
                    Assertions.assertNotNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.CREATED.value(), responseAPI.code());

                    System.out.println("message : " + responseAPI.message());
                    System.out.println("data : "  + responseAPI.data() );
                }
        );
    }

    @Test
    void regisFailedBecauseNameIsBlankSuccess() throws Exception{
        RequestRegisStoreDTO regisStoreDTO = new RequestRegisStoreDTO();
        regisStoreDTO.setName("");
        regisStoreDTO.setAddress("Jakarta Selatan");
        regisStoreDTO.setCellphone("082209874632");

        mockMvc.perform(
                post("/api/create/store")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regisStoreDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseAPI.code());

                    System.out.println("message : " + responseAPI.message());
                }
        );
    }

    @Test
    void regisFailedBecauseNameIsEmptySuccess() throws Exception{
        RequestRegisStoreDTO regisStoreDTO = new RequestRegisStoreDTO();
        regisStoreDTO.setName(" ");
        regisStoreDTO.setAddress("Jakarta Selatan");
        regisStoreDTO.setCellphone("082209874632");

        mockMvc.perform(
                post("/api/create/store")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regisStoreDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseAPI.code());

                    System.out.println("message : " + responseAPI.message());
                }
        );
    }
}
