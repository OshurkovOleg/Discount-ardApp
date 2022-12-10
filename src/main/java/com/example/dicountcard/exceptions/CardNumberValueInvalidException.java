package com.example.dicountcard.exceptions;

import lombok.Data;

@Data
public class CardNumberValueInvalidException extends RuntimeException {
    public CardNumberValueInvalidException(String message) {
        super(message);
    }
}
