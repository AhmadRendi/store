package com.example.estore.test.product;

import com.example.estore.dto.request.RequestLogin;
import com.example.estore.dto.request.SearchDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.dto.response.ResponseProductList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class TestSearchProductByName {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String out = "Authorization";
    private static final String bear = "Bearer ";

    private  String loginn() throws Exception{
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
    void testSearchSuccess() throws Exception {
        SearchDTO searchDTO = new SearchDTO();

        String token = loginn();

        searchDTO.setName("cold");

        mockMvc.perform(
                get("/api/product/search")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchDTO))
                        .header(out, bear + token)

        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseProductList productList = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseProductList.class);

                    Assertions.assertNotNull(productList.data());
                    Assertions.assertNull(productList.error());

                    System.out.println("data : " + productList.data());
                }
        );
    }

    @Test
    void testSearchFailedBecauseSearchEmpty() throws Exception {
        SearchDTO searchDTO = new SearchDTO();

        String token = loginn();

        searchDTO.setName("");

        mockMvc.perform(
                get("/api/product/search")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchDTO))
                        .header(out, bear + token)
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseProductList productList = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseProductList.class);

                    Assertions.assertNull(productList.data());
                    Assertions.assertNotNull(productList.error());
                    Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), productList.code());

                    System.out.println("message : " + productList.message());
                    System.out.println("error : " + productList.error());
                    System.out.println("code : " + productList.code());
                }
        );
    }

    @Test
    void testSearchFailedBecauseSearchBlank() throws Exception {
        SearchDTO searchDTO = new SearchDTO();

        String token = loginn();

        searchDTO.setName(" ");

        mockMvc.perform(
                get("/api/product/search")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchDTO))
                        .header(out, bear + token)
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseProductList productList = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseProductList.class);

                    Assertions.assertNull(productList.data());
                    Assertions.assertNotNull(productList.error());
                    Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), productList.code());

                    System.out.println("message : " + productList.message());
                    System.out.println("error : " + productList.error());
                    System.out.println("code : " + productList.code());
                }
        );
    }
}
