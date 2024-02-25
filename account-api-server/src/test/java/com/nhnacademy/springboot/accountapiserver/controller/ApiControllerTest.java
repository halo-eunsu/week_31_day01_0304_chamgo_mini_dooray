package com.nhnacademy.springboot.accountapiserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springboot.accountapiserver.domain.Account;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(0)
    void testGetAccounts() throws Exception {
        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", equalTo("1")));
    }

    @Test
    @Order(1)
    void testGetStudent() throws Exception { // throw EXCEPTION  해주셈
        mockMvc.perform(get("/api/accounts/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo("1")));
    }

    @Test
    @Order(3)
    void testCreateStudent() throws Exception {
        Account input = new Account("testId", "testPassword", "testEmail", "testName");
        ObjectMapper obj = new ObjectMapper();
        mockMvc.perform(
                        post("/api/accounts")
                                .content(obj.writeValueAsString(input))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("testName")))
                .andExpect(jsonPath("$.password", equalTo("testPassword")));
    }

    @Test
    @Order(4)
    void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/api/accounts/{id}", "testId"))
                .andExpect(status().isNoContent());
    }
}
