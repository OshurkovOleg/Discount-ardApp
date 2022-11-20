package com.example.dicountcard.controller;

import com.example.dicountcard.model.Client;
import com.example.dicountcard.services.ClientService;
import com.example.dicountcard.util.CardNumberValueInvalidException;
import com.example.dicountcard.util.ClientErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.dicountcard.constants.Constants.*;

@RestController
@RequestMapping("/api")
public class DiscountController {
    ClientService clientService;

    @Autowired
    public DiscountController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("clients/all")
    public List<Client> getAllCardClients() {
        return clientService.getAll();
    }

    @GetMapping("clients/{card}")
    public Client getOrAddClientCard(@PathVariable long card) {
        return clientService.get(card);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handlerException(CardNumberValueInvalidException e) {
        ClientErrorResponse response = new ClientErrorResponse(THE_CARD_NUMBER_CANNOT_BE_NEGATIVE);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}