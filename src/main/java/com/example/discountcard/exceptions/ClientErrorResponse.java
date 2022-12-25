package com.example.discountcard.exceptions;

import lombok.Data;

@Data
public class ClientErrorResponse {
    private String message;

    public ClientErrorResponse(String message) {
        this.message = message;

    }
}
