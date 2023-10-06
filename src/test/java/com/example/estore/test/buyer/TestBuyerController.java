package com.example.estore.test.buyer;


import com.example.estore.dto.request.RequestRegisBuyerDTO;
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
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("ahmadrendiajah@gmail.com");
        requestRegisBuyerDTO.setUsername("ahmadrendi");
        requestRegisBuyerDTO.setPassword("@hmAd21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
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

    @Test
    void registerFailedBecauseEmailNotValid() throws Exception{
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("ahmadrendi@gmail.");
        requestRegisBuyerDTO.setUsername("ahmadrendi22156uibi");
        requestRegisBuyerDTO.setPassword("@hmAd21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);


                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );


                    System.out.println("message : " + responseAPI.message());
                }
        );
    }

    @Test
    void registrationFailedBecauseEmailUsingWhiteSpace() throws Exception{
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("ahmad rendi@gmail.com");
        requestRegisBuyerDTO.setUsername("ahmadrendi22156uibi");
        requestRegisBuyerDTO.setPassword("@hmAd21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);


                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );


                    System.out.println("message : " + responseAPI.message());
                }
        );
    }

    @Test
    void registerFailedBecauseEmailBlank() throws Exception{
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail(" ");
        requestRegisBuyerDTO.setUsername("ahmadrendi22156");
        requestRegisBuyerDTO.setPassword("@hmAd21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);


                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );


                    System.out.println("message : " + responseAPI.message());
                }
        );
    }

    @Test
    void registerFailedBecauseEmailEmpty() throws Exception{
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("");
        requestRegisBuyerDTO.setUsername("ahmadrendi22156");
        requestRegisBuyerDTO.setPassword("@hmAd21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);
                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );

                    System.out.println("message : " + responseAPI.message());
                }
        );
    }

    @Test
    void registerFailedBecauseEmailIsReady()throws Exception {
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("ahmadrendiajah@gmail.com");
        requestRegisBuyerDTO.setUsername("ahmadrendi2");
        requestRegisBuyerDTO.setPassword("@hmAd21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);
                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );

                    System.out.println("message : " + responseAPI.message());
                }
        );
    }

    @Test
    void registerFailedBecauseUsernameNotValidWhiteSpace() throws Exception {
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("ahmadrendi@gmail.com");
        requestRegisBuyerDTO.setUsername("ahmad rendi");
        requestRegisBuyerDTO.setPassword("@hmAd21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );

                    System.out.println("message : " + responseAPI.message());
                    for (var value : responseAPI.error()){
                        System.out.println("error : " + value);
                    }
                }
        );
    }

    @Test
    void registrationFailedBecauseUsernameIsAlReady() throws Exception{
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("ahmadrendi@gmail.com");
        requestRegisBuyerDTO.setUsername("ahmadrendi22156");
        requestRegisBuyerDTO.setPassword("@hmAd21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );

                    System.out.println("message : " + responseAPI.message());
                    for (var value : responseAPI.error()){
                        System.out.println("error : " + value);
                    }
                }
        );
    }

    @Test
    void registrationFailedBecauseUsernameIsBlank() throws Exception{
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("ahmadrendi@gmail.com");
        requestRegisBuyerDTO.setUsername(" ");
        requestRegisBuyerDTO.setPassword("@hmAd21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );

                    System.out.println("message : " + responseAPI.message());
                    for (var value : responseAPI.error()){
                        System.out.println("error : " + value);
                    }
                }
        );
    }

    @Test
    void registrationFailedBecauseUsernameIsEmpty() throws Exception{
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("ahmadrendi@gmail.com");
        requestRegisBuyerDTO.setUsername("");
        requestRegisBuyerDTO.setPassword("@hmAd21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );

                    System.out.println("message : " + responseAPI.message());
                    for (var value : responseAPI.error()){
                        System.out.println("error : " + value);
                    }
                }
        );
    }

    @Test
    void registrationFailedBecausePasswordNotUsingToSymbol() throws Exception{
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("ahmadrendi@gmail.com");
        requestRegisBuyerDTO.setUsername("ahmad");
        requestRegisBuyerDTO.setPassword("ahmAd21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );

                    System.out.println("message : " + responseAPI.message());
                    for (var value : responseAPI.error()){
                        System.out.println("error : " + value);
                    }
                }
        );
    }

    @Test
    void registrationFailedBecausePasswordNotUsingToNumber() throws Exception{
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("ahmadrendi@gmail.com");
        requestRegisBuyerDTO.setUsername("ahmad");
        requestRegisBuyerDTO.setPassword("@hmAd");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );

                    System.out.println("message : " + responseAPI.message());
                    for (var value : responseAPI.error()){
                        System.out.println("error : " + value);
                    }
                }
        );
    }

    @Test
    void registrationFailedBecausePasswordNotUsingUpperCase() throws Exception{
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("ahmadrendi@gmail.com");
        requestRegisBuyerDTO.setUsername("ahmad");
        requestRegisBuyerDTO.setPassword("@hmad21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );

                    System.out.println("message : " + responseAPI.message());
                    for (var value : responseAPI.error()){
                        System.out.println("error : " + value);
                    }
                }
        );
    }

    @Test
    void registrationFailedBecausePasswordNotUsingLowerCase() throws Exception {
        RequestRegisBuyerDTO requestRegisBuyerDTO = new RequestRegisBuyerDTO();

        requestRegisBuyerDTO.setEmail("ahmadrendi@gmail.com");
        requestRegisBuyerDTO.setUsername("ahmad");
        requestRegisBuyerDTO.setPassword("@HMAD21");
        requestRegisBuyerDTO.setRoles("BUYER");

        mockMvc.perform(
                post("/api/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisBuyerDTO))
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNotNull(responseAPI.error());
                    Assertions.assertNull(responseAPI.data());
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseAPI.code() );

                    System.out.println("message : " + responseAPI.message());
                    for (var value : responseAPI.error()){
                        System.out.println("error : " + value);
                    }
                }
        );
    }
}
