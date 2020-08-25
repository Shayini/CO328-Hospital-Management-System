package com.example.ehospital.Controller;

import com.example.ehospital.EhospitalApplicationTests;
import com.example.ehospital.Model.Booking;
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

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

public class BookingControllerTest extends EhospitalApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void addBooking() throws Exception {
        String uri = "/Project/REST-API/user/1/addbooking/1/27";
        Booking booking = new Booking();
        booking.setDepartment("Cardiologist");
        booking.setDate(Date.valueOf("2020-08-30"));
        booking.setTime(Time.valueOf("10:00:00"));
        booking.setContact_no(771234567);
        booking.setEmail("user1@gmail.com");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(booking);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        String expected = "Booked Successful";
        System.out.println("\nActual: " + result);
        System.out.println("Expected: " + expected + "\n");
        assertEquals(expected, result);
    }


    @Test
    public void deleteUserBooking() throws Exception {
        String uri = "/Project/REST-API/doctor/1/deletebooking/96";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        String expected = "Booking Cancelled Successfully";
        System.out.println("\nActual: " + result);
        System.out.println("Expected: " + expected + "\n");
        assertEquals(expected, result);
    }
}