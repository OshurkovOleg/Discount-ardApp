package com.example.discountcard.controller;

import com.example.discountcard.exceptions.CardNotFoundException;
import com.example.discountcard.exceptions.CheckNotCreatedException;
import com.example.discountcard.exceptions.ClientErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerAdvice {

    @ExceptionHandler(CheckNotCreatedException.class)
    public ResponseEntity<ClientErrorResponse> handlerExceptionBadRequest(CheckNotCreatedException e) {
        ClientErrorResponse response = new ClientErrorResponse(e.getMessage());
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ClientErrorResponse> handlerExceptionNotFound(CardNotFoundException e) {
        ClientErrorResponse response = new ClientErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        //TODO билдер
    }

}
