package com.example.discountcard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
public class ApplicationTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(applicationContext);
    }
}


