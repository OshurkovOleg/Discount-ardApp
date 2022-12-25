package com.example.discountcard.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class DiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllClients_ShouldReturnAllClientFromTableClient() throws Exception {
        this.mockMvc.perform(get("/api/clients/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("2087")));
    }

    @Test
    public void testGetClient_ShouldReturnClientFromTableClient_WhenRequestByNumberCard() throws Exception {
        this.mockMvc.perform(get("/api/clients/123456")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("123456")));
    }


}