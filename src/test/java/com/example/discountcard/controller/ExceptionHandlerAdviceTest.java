package com.example.discountcard.controller;

import com.example.discountcard.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ExceptionHandlerAdviceTest {

    private MockMvc mockMvc;
    private final ExceptionHandlerAdvice exceptionHandlerAdvice;

    private final ObjectMapper objectMapper;

    @Autowired
    public ExceptionHandlerAdviceTest(MockMvc mockMvc, ExceptionHandlerAdvice exceptionHandlerAdvice, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.exceptionHandlerAdvice = exceptionHandlerAdvice;
        this.objectMapper = objectMapper;
    }

/*    @Test
    public void shouldCatchIfThrowCheckNotCreatedException() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(DiscountController.class)
                .setControllerAdvice(new ExceptionHandlerAdvice())
                .build();


        File checkFileJson = new File("src/test/resources/endpoint.checks/checkNumberNull.json");

        String checkStr = objectMapper.writeValueAsString(checkFileJson);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/checks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(checkStr)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String errorMessage = result.getResponse().getErrorMessage();
        System.out.println(errorMessage);
        Assertions.assertNotNull(errorMessage);

    }*/
}
