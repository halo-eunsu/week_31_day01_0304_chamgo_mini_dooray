package com.nhnacademy.mini.dooray.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.mini.dooray.domain.Account;
import com.nhnacademy.mini.dooray.domain.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountRepositoryTest {

    @MockBean
    AccountRepository accountRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetAccounts() throws Exception {

        given(accountRepository.findAll()).willReturn(List.of(new Account("id","pwd","email", "name")));

        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", equalTo("id")));
    }

    @Test
    void testGetStudent() throws Exception {
        given(accountRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(new Account("id", "pwd", "email", "name")));

        mockMvc.perform(get("/api/accounts/{id}", "id"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", equalTo("email")));
    }

    @Test
    void testCreateAccount() throws Exception {
        Account input = new Account("id", "pwd", "email", "name");
        ObjectMapper obj = new ObjectMapper();

        given(accountRepository.save(input)).willReturn(input);

        mockMvc.perform(post("/api/accounts")
                        .content(obj.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("name")))
                .andExpect(jsonPath("$.email", equalTo("email")));
    }


}