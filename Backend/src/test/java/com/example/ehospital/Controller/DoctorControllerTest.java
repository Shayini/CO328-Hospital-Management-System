package com.example.ehospital.Controller;

import com.example.ehospital.EhospitalApplicationTests;
import com.example.ehospital.Model.Doctor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorControllerTest extends EhospitalApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void addDoctors() throws Exception {
        String uri = "/Project/REST-API/adddoctor";
        Doctor doctor = new Doctor();
        doctor.setName("Ravirajh");
        doctor.setUsername("ravirajh");
        doctor.setPassword("1n2jek4i");
        doctor.setDepartment("Cardiologist");
        doctor.setSpecialization("Surgeon");
        doctor.setAge(30);
        doctor.setContact_no(776543219);
        doctor.setEmail("ravirajh@gmail.com");
        doctor.setAddress("Colombo");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(doctor);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        String expected = "Doctor account created successfully";
        System.out.println("\nActual: " + result);
        System.out.println("Expected: " + expected + "\n");
        assertEquals(expected, result);
    }


    @Test
    public void findByDoctorID() throws Exception {
        String uri = "/Project/REST-API/doctor/35";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        String expected = "{\"name\":\"Ravirajh\",\"address\":\"Colombo\",\"active\":false,\"email\":\"ravirajh@gmail.com\",\"age\":30,\"contact_no\":776543219,\"department\":\"Cardiologist\",\"specialization\":\"Surgeon\",\"doctorID\":35}";
        System.out.println("\nActual: " + result);
        System.out.println("Expected: " + expected + "\n");
        JSONAssert.assertEquals(expected,result, JSONCompareMode.STRICT);
    }

    @Test
    public void deleteDoctor() throws Exception {
        String uri = "/Project/REST-API/deletedoctor/35";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        String expected = "Doctor Deleted Successfully";
        System.out.println("\nActual: " + result);
        System.out.println("Expected: " + expected + "\n");
        assertEquals(expected, result);
    }
}