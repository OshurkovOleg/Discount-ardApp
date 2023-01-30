package com.example.discountcard.controller;

import com.example.discountcard.Application;
import com.example.discountcard.dto.CheckPackDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllClients_ShouldReturnAllClientFromTableClient() throws Exception {
        mockMvc.perform(get("/api/clients/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("231")));
    }

    @Test
    public void testGetClient_ShouldReturnClientFromTableClient_WhenRequestByNumberCard() throws Exception {
        mockMvc.perform(get("/api/clients/23499921"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("23499921")));
    }

    @Test
    public void testSaveNewCheck_ShouldSaveNewCheckInBD() throws Exception {

        File file = new File("src/test/resources/check.json");
        CheckPackDTO checkPackDTO = objectMapper.readValue(file, CheckPackDTO.class);


        String checkPost = objectMapper.writeValueAsString(checkPackDTO);


        mockMvc.perform(post("/api/checks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(checkPost)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

}