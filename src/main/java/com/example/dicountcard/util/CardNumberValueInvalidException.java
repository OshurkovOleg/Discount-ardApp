package com.example.dicountcard.util;

import lombok.Data;

@Data
public class CardNumberValueInvalidException extends RuntimeException {
    public CardNumberValueInvalidException(String message) {
        super(message);
    }
}
