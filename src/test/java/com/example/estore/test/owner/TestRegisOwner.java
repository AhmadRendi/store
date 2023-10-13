package com.example.estore.test.owner;

import com.example.estore.dto.request.RequestRegisOwnerDTO;
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
public class TestRegisOwner {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void regisSuccess() throws Exception{
        RequestRegisOwnerDTO regisOwnerDTO = new RequestRegisOwnerDTO();


        regisOwnerDTO.setEmails("ahmad@gmail.com");
        regisOwnerDTO.setUsernames("ahmad");
        regisOwnerDTO.setPasswords("@lhAm90");
        regisOwnerDTO.setNames("Ahmad");
        regisOwnerDTO.setRole("OWNER");


        mockMvc.perform(
                post("/api/registration/owner")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regisOwnerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNotNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.CREATED.value(), responseAPI.code());
                    Assertions.assertNull(responseAPI.error());

                    System.out.println("Token : " + responseAPI.token());
                    System.out.println("message " + responseAPI.message());
                }
        );
    }

    @Test
    void regisFailedBecauseEmailIsAlReadySuccess() throws Exception{
        RequestRegisOwnerDTO regisOwnerDTO = new RequestRegisOwnerDTO();


        regisOwnerDTO.setEmails("ahmad@gmail.com");
        regisOwnerDTO.setUsernames("ahmad_ren");
        regisOwnerDTO.setPasswords("@lhAm90");
        regisOwnerDTO.setNames("Ahmad");
        regisOwnerDTO.setRole("OWNER");


        mockMvc.perform(
                post("/api/registration/owner")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regisOwnerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseAPI.code());
                    Assertions.assertNotNull(responseAPI.error());

                    System.out.println("message " + responseAPI.message());
                }
        );
    }

    @Test
    void regisFailedBecauseUsernameIsAlReadySuccess() throws Exception{
        RequestRegisOwnerDTO regisOwnerDTO = new RequestRegisOwnerDTO();

        regisOwnerDTO.setEmails("ahmadrendiajah@gmail.com");
        regisOwnerDTO.setUsernames("ahmad");
        regisOwnerDTO.setPasswords("@lhAm90");
        regisOwnerDTO.setNames("Ahmad");
        regisOwnerDTO.setRole("OWNER");

        mockMvc.perform(
                post("/api/registration/owner")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regisOwnerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseAPI.code());
                    Assertions.assertNotNull(responseAPI.error());

                    System.out.println("message " + responseAPI.message());
                }
        );
    }

    @Test
    void regisFailedBecausePasswordNotUsingNumberIsAlReadySuccess() throws Exception{
        RequestRegisOwnerDTO regisOwnerDTO = new RequestRegisOwnerDTO();

        regisOwnerDTO.setEmails("ahmadrendiajah@gmail.com");
        regisOwnerDTO.setUsernames("ahmad_ren");
        regisOwnerDTO.setPasswords("@lhAm");
        regisOwnerDTO.setNames("Ahmad");
        regisOwnerDTO.setRole("OWNER");

        mockMvc.perform(
                post("/api/registration/owner")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regisOwnerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseAPI.code());
                    Assertions.assertNotNull(responseAPI.error());

                    System.out.println("message " + responseAPI.message());
                }
        );
    }

    @Test
    void regisFailedBecausePasswordNotUsingSymbolIsAlReadySuccess() throws Exception{
        RequestRegisOwnerDTO regisOwnerDTO = new RequestRegisOwnerDTO();

        regisOwnerDTO.setEmails("ahmadrendiajah@gmail.com");
        regisOwnerDTO.setUsernames("ahmad_ren");
        regisOwnerDTO.setPasswords("alhAm90");
        regisOwnerDTO.setNames("Ahmad");
        regisOwnerDTO.setRole("OWNER");

        mockMvc.perform(
                post("/api/registration/owner")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regisOwnerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseAPI.code());
                    Assertions.assertNotNull(responseAPI.error());

                    System.out.println("message " + responseAPI.message());
                }
        );
    }

    @Test
    void regisFailedBecausePasswordNotUsingUpperCaseIsAlReadySuccess() throws Exception{
        RequestRegisOwnerDTO regisOwnerDTO = new RequestRegisOwnerDTO();

        regisOwnerDTO.setEmails("ahmadrendiajah@gmail.com");
        regisOwnerDTO.setUsernames("ahmad_ren");
        regisOwnerDTO.setPasswords("@lham90");
        regisOwnerDTO.setNames("Ahmad");
        regisOwnerDTO.setRole("OWNER");

        mockMvc.perform(
                post("/api/registration/owner")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regisOwnerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseAPI.code());
                    Assertions.assertNotNull(responseAPI.error());

                    System.out.println("message " + responseAPI.message());
                }
        );
    }
}
