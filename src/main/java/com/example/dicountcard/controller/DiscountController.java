package com.example.dicountcard.controller;

import com.example.dicountcard.dto.CheckPackDTO;
import com.example.dicountcard.exceptions.CardNotFoundException;
import com.example.dicountcard.exceptions.CheckNotCreatedException;
import com.example.dicountcard.exceptions.ClientErrorResponse;
import com.example.dicountcard.model.Client;
import com.example.dicountcard.services.CheckService;
import com.example.dicountcard.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")

public class DiscountController {
    private final ClientService clientService;
    private final CheckService checkService;


    @Autowired
    public DiscountController(ClientService clientService, CheckService checkService) {
        this.clientService = clientService;
        this.checkService = checkService;
    }

    @GetMapping("clients/all")
    public List<Client> getAllCardClients() {
        return clientService.getAll();
    }

    @GetMapping("clients/{card}")
    public Client getOrAddClientCard(@PathVariable long card) {
        return clientService.getByCardNumber(card);
    }

    @PostMapping("/checks")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CheckPackDTO checkPackDTO) {
        checkService.save(checkPackDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handlerException(CheckNotCreatedException e) {
        ClientErrorResponse response = new ClientErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handlerException(CardNotFoundException e) {
        ClientErrorResponse response = new ClientErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
