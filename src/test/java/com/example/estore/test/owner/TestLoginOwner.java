package com.example.estore.test.owner;


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

@AutoConfigureMockMvc
@SpringBootTest
public class TestLoginOwner {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void testLoginSuccess() throws Exception{
        RequestLogin login = new RequestLogin();

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

                    Assertions.assertNull(responseAPI.error());
                    Assertions.assertNotNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.OK.value(), responseAPI.code());

                    System.out.println("data : " + responseAPI.data());
                    System.out.println("token : " + responseAPI.token());
                }
        );
    }

    @Test
    void testLoginFailedBecausePasswordIsWrong() throws Exception{
        RequestLogin login = new RequestLogin();

        login.setEmail("ahmadrendi@gmail.com");
        login.setPassword("@lhAm9");

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

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), responseAPI.code());

                    System.out.println("message : " + responseAPI.message());
                }
        );
    }

    @Test
    void testLoginFailedBecauseEmailNotFoundSuccess() throws Exception{
        RequestLogin login = new RequestLogin();

        login.setEmail("ahmadren@gmail.com");
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

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), responseAPI.code());

                    System.out.println("message : " + responseAPI.message());
                }
        );
    }


    @Test
    void testLoginFailedBecauseEmailBlank() throws Exception{
        RequestLogin login = new RequestLogin();

        login.setEmail("");
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

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseAPI.code());

                    System.out.println("message : " + responseAPI.message());
                }
        );
    }

    @Test
    void testLoginFailedBecauseEmailEmpty() throws Exception{
        RequestLogin login = new RequestLogin();

        login.setEmail(" ");
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

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseAPI.code());

                    System.out.println("message : " + responseAPI.message());
                }
        );
    }

    @Test
    void testLoginFailedBecausePasswordBlank() throws Exception{
        RequestLogin login = new RequestLogin();

        login.setEmail("ahmadrendi@gmail.com");
        login.setPassword("");

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

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseAPI.code());

                    System.out.println("message : " + responseAPI.message());
                }
        );
    }

    @Test
    void testLoginFailedBecausePasswordEmpty() throws Exception{
        RequestLogin login = new RequestLogin();

        login.setEmail("ahmadrendi@gmail.com");
        login.setPassword(" ");

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

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseAPI.code());

                    System.out.println("message : " + responseAPI.message());
                }
        );
    }
}
