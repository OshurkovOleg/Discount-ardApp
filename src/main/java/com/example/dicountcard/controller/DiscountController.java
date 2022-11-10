package com.example.dicountcard.controller;

import org.hibernate.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscountController {

    @GetMapping("users")
    public ResponseEntity<List<String>> getAllCardClient(String str) {

        return ResponseEntity.ok(List.of("sdfdsf", "sdfdsf"));
    }
}
