package com.example.estore.test.store;

import com.example.estore.dto.request.RequestLogin;
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

    private static final String out = "Authorization";
    private static final String bear = "Bearer ";

    private  String login() throws Exception{
        RequestLogin login = new RequestLogin();

        final String[] token = new String[1];

        login.setEmail("ahmadrendi@gmail.com");
        login.setPassword("@lhAm90");


        mockMvc.perform(
                get("/api/login/owner")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    token[0] = responseAPI.token();
                }
        );

        return token[0];
    }

    @Test
    void regisSuccess() throws Exception{
        RequestRegisStoreDTO regisStoreDTO = new RequestRegisStoreDTO();
        regisStoreDTO.setName("store book");
        regisStoreDTO.setAddress("Tanggerang City");
        regisStoreDTO.setCellphone("082209874632");

        String token = login();

        mockMvc.perform(
                post("/api/store/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regisStoreDTO))
                        .header(out ,bear + token)
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
