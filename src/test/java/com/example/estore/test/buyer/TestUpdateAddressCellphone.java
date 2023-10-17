package com.example.estore.test.buyer;

import com.example.estore.Entity.Buyer;
import com.example.estore.dto.request.RequestLogin;
import com.example.estore.dto.request.RequestUpdateAddressCellphoneBuyer;
import com.example.estore.dto.response.ResponseAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class TestUpdateAddressCellphone {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String out = "Authorization";
    private static final String bear = "Bearer ";

    private  AcceptData<Buyer> login() throws Exception{

        RequestLogin loginBuyer = new RequestLogin();

        loginBuyer.setEmail("ahmadrendiajah@gmail.com");
        loginBuyer.setPassword("@hmAd21");



        MvcResult mvcResult = mockMvc.perform(
                get("/api/user/login")
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
        AcceptData<Buyer> acceptData = login();
        String token = acceptData.token;
        String data = String.valueOf(acceptData.data);
        Long id = id(data);
        List<String> list = new ArrayList<>();
        list.add(token);
        list.add(String.valueOf(id));

        return list;
    }
    @Test
    void testUpdateSuccess() throws Exception {
        RequestUpdateAddressCellphoneBuyer updateAddressCellphoneBuyer = new RequestUpdateAddressCellphoneBuyer();

        String token = accept().get(0);
        Long id = Long.valueOf(accept().get(1));

        updateAddressCellphoneBuyer.setAddress("Makassar");
        updateAddressCellphoneBuyer.setCellphone("082292091616");
        updateAddressCellphoneBuyer.setId(id);


        mockMvc.perform(
                put("/api/user/update/address/cellphone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateAddressCellphoneBuyer))
                        .header(out, bear + token)

        ).andExpectAll(
                status().isOk()
        ).andDo(
                result -> {
                    ResponseAPI<?> responseAPI = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseAPI.class);

                    Assertions.assertNull(responseAPI.error());
                    Assertions.assertEquals(HttpStatus.OK.value(), responseAPI.code());

                    System.out.println("message : " + responseAPI.message());
                }
        );
    }


    @Test
    void updateFailedBecauseCellphoneIsMin() throws Exception{
        RequestUpdateAddressCellphoneBuyer updateAddressCellphoneBuyer = new RequestUpdateAddressCellphoneBuyer();

        String token = accept().get(0);
        Long id = Long.valueOf(accept().get(1));

        updateAddressCellphoneBuyer.setAddress("Makassar");
        updateAddressCellphoneBuyer.setCellphone("0822920916");
        updateAddressCellphoneBuyer.setId(id);


        mockMvc.perform(
                put("/api/user/update/address/cellphone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateAddressCellphoneBuyer))
                        .header(out, bear + token)

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
    void updateFailedBecauseCellphoneMax() throws Exception{
        RequestUpdateAddressCellphoneBuyer updateAddressCellphoneBuyer = new RequestUpdateAddressCellphoneBuyer();

        String token = accept().get(0);
        Long id = Long.valueOf(accept().get(1));

        updateAddressCellphoneBuyer.setAddress("Makassar");
        updateAddressCellphoneBuyer.setCellphone("08229209161616");
        updateAddressCellphoneBuyer.setId(id);


        mockMvc.perform(
                put("/api/user/update/address/cellphone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateAddressCellphoneBuyer))
                        .header(out, bear + token)

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
    void updateFailedBecauseCellphoneIsEmpty() throws Exception{
        RequestUpdateAddressCellphoneBuyer updateAddressCellphoneBuyer = new RequestUpdateAddressCellphoneBuyer();

        String token = accept().get(0);
        Long id = Long.valueOf(accept().get(1));

        updateAddressCellphoneBuyer.setAddress("Makassar");
        updateAddressCellphoneBuyer.setCellphone("");
        updateAddressCellphoneBuyer.setId(id);


        mockMvc.perform(
                put("/api/user/update/address/cellphone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateAddressCellphoneBuyer))
                        .header(out, bear + token)

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
    void updateFailedBecauseNumberIsBlank() throws Exception  {
        RequestUpdateAddressCellphoneBuyer updateAddressCellphoneBuyer = new RequestUpdateAddressCellphoneBuyer();

        String token = accept().get(0);
        Long id = Long.valueOf(accept().get(1));

        updateAddressCellphoneBuyer.setAddress("Makassar");
        updateAddressCellphoneBuyer.setCellphone(" ");
        updateAddressCellphoneBuyer.setId(id);


        mockMvc.perform(
                put("/api/user/update/address/cellphone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateAddressCellphoneBuyer))
                        .header(out, bear + token)

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
    void updateFailedBecauseAddressIsEmpty() throws Exception{
        RequestUpdateAddressCellphoneBuyer updateAddressCellphoneBuyer = new RequestUpdateAddressCellphoneBuyer();

        String token = accept().get(0);
        Long id = Long.valueOf(accept().get(1));

        updateAddressCellphoneBuyer.setAddress("");
        updateAddressCellphoneBuyer.setCellphone("082292091616");
        updateAddressCellphoneBuyer.setId(id);


        mockMvc.perform(
                put("/api/user/update/address/cellphone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateAddressCellphoneBuyer))
                        .header(out, bear + token)

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
    void updateFailedBecauseAddressIsBlank() throws Exception {
        RequestUpdateAddressCellphoneBuyer updateAddressCellphoneBuyer = new RequestUpdateAddressCellphoneBuyer();

        String token = accept().get(0);
        Long id = Long.valueOf(accept().get(1));

        updateAddressCellphoneBuyer.setAddress(" ");
        updateAddressCellphoneBuyer.setCellphone("082292091616");
        updateAddressCellphoneBuyer.setId(id);


        mockMvc.perform(
                put("/api/user/update/address/cellphone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateAddressCellphoneBuyer))
                        .header(out, bear + token)

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
