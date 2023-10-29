package com.example.estore.test.store;

import com.example.estore.Entity.Store;
import com.example.estore.dto.request.RequestLogin;
import com.example.estore.dto.request.SearchDTO;
import com.example.estore.dto.response.ResponseAPI;
import com.example.estore.dto.response.ResponseListAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class TestSearchNameStore {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String out = "Authorization";
    private static final String bear = "Bearer ";

    private AcceptData<Store> login() throws Exception{

        RequestLogin loginBuyer = new RequestLogin();

        loginBuyer.setEmail("ahmadrendi@gmail.com");
        loginBuyer.setPassword("@lhAm90");



        MvcResult mvcResult = mockMvc.perform(
                get("/api/owner/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginBuyer))
        ).andExpectAll(
                status().isOk()
        ).andReturn();

        String accept = mvcResult.getResponse().getContentAsString();

        return objectMapper.readValue(accept, AcceptData.class);
    }


    @Data
    public static class AcceptData<T>{
        private String token;
        private Long id;
        private T data;
    }

    private static List<String> resultSplit(String data){
        String[] split = data.split(", ");
        return new ArrayList<>(Arrays.asList(split));
    }

    private Long id(String token){

        List<String> list = resultSplit(token);
        List<String> list1;
        long id = 0L;

        for(int i = 0; i < list.size(); i++){
            if(i == 3){
                list1 = List.of(list.get(i).split("="));
                for (int k = 0; k < list1.size(); k++){
                    if(k == 1){
                        id = Long.parseLong(list1.get(k));
                        break;
                    }
                }
                break;
            }
        }
        return id;
    }

    private List<String> accept() throws Exception {
        AcceptData<Store> acceptData = login();
        String token = acceptData.getToken();
        String data = String.valueOf(acceptData.data);
        Long id = id(data);
        List<String> list = new ArrayList<>();
        list.add(token);
        list.add(String.valueOf(id));

        return list;
    }


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
    void searchSuccess() throws Exception{

        SearchDTO search = new SearchDTO();

        search.setName("PT Grammedia");


        String token = loginn();
        System.out.println(token);

        mockMvc.perform(
                get("/api/store/search/name")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(objectMapper.writeValueAsString(search))
                        .header(out, bear + token)
        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseListAPI<?> responseListAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseListAPI.class);


                    System.out.println(responseListAPI.code());
                }
        );
    }
}
