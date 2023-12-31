package com.example.estore.test.buyer;


import com.example.estore.dto.request.RequestLogin;
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

@SpringBootTest
@AutoConfigureMockMvc
public class TestLoginBuyer {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testLoginSuccess() throws Exception {
        RequestLogin loginBuyer = new RequestLogin();

        loginBuyer.setEmail("ahmadrendiajah@gmail.com");
        loginBuyer.setPassword("@hmAd21");

        mockMvc.perform(
                get("/api/login/buyer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginBuyer))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNotNull(responseAPI.data());
                    Assertions.assertNull(responseAPI.error());
                    Assertions.assertEquals(HttpStatus.FOUND.value(), responseAPI.code());

                    System.out.println("message " + responseAPI.data());
                }
        );
    }


    @Test
    void testLoginFailedBecauseEmailNotFound() throws Exception {
        RequestLogin loginBuyer = new RequestLogin();

        loginBuyer.setEmail("ahmadrendiajah@gmail.com");
        loginBuyer.setPassword("@hmAd21");

        mockMvc.perform(
                get("/api/user/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginBuyer))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), responseAPI.code());

                    System.out.println("message " + responseAPI.message());
                }
        );

    }

    @Test
    void testLoginFailedBecauseEmailNotValid() throws Exception{
        RequestLogin loginBuyer = new RequestLogin();

        loginBuyer.setEmail("ahmadrendi");
        loginBuyer.setPassword("@hmAd21");

        mockMvc.perform(
                get("/api/user/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginBuyer))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), responseAPI.code());

                    System.out.println("message " + responseAPI.message());
                }
        );
    }
}
