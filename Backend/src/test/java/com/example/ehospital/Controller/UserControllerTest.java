package com.example.ehospital.Controller;

import com.example.ehospital.EhospitalApplicationTests;
import com.example.ehospital.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest extends EhospitalApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void checkUsername() throws Exception {
        String uri = "/Project/REST-API/checkusername?username=user1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        String expected = "Username already Exist";
        System.out.println("\nActual: " + result);
        System.out.println("Expected: " + expected + "\n");
        assertEquals(expected, result);
    }

    @Test
    public void addUser() throws Exception {
        String uri = "/Project/REST-API/adduser";
        User user = new User();
        user.setName("Prithvi");
        user.setUsername("prithvi");
        user.setPassword("1ge74tr5");
        user.setAge(20);
        user.setAddress("Kandy");
        user.setContact_no(757656789);
        user.setEmail("user1@gmail.com");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(user);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        String expected = "Email already exist";
        System.out.println("\nActual: " + result);
        System.out.println("Expected: " + expected + "\n");
        assertEquals(expected, result);
    }
}