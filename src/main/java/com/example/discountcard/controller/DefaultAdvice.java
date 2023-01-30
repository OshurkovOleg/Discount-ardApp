package com.example.discountcard.controller;

import com.example.discountcard.exceptions.CardNotFoundException;
import com.example.discountcard.exceptions.CheckNotCreatedException;
import com.example.discountcard.exceptions.ClientErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class DefaultAdvice {

    @ExceptionHandler(CheckNotCreatedException.class)
    private ResponseEntity<ClientErrorResponse> handlerException(CheckNotCreatedException e) {
        ClientErrorResponse response = new ClientErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardNotFoundException.class)
    private ResponseEntity<ClientErrorResponse> handlerException(CardNotFoundException e) {
        ClientErrorResponse response = new ClientErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
