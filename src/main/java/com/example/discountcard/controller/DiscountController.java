package com.example.discountcard.controller;

import com.example.discountcard.dto.CheckPackDTO;
import com.example.discountcard.entities.ClientEntity;
import com.example.discountcard.services.CheckService;
import com.example.discountcard.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(description = "Главный контроллер")
public class DiscountController {
    private final ClientService clientService;
    private final CheckService checkService;


    @Autowired
    public DiscountController(ClientService clientService, CheckService checkService) {
        this.clientService = clientService;
        this.checkService = checkService;
    }

    @GetMapping("clients/all")
    @ApiOperation("Получить карты всех клиентов")
    public List<ClientEntity> getAllClients() {
        return clientService.getAll();
    }

    @GetMapping("clients/{card}")
    @ApiOperation("Получить карту по номеру")
    public ClientEntity getClient(@PathVariable long card) {
        return clientService.getByCardNumber(card);
    }

    @PostMapping("/checks")
    @ApiOperation("Сохранение входящего чека в БД")
    public ResponseEntity<HttpStatus> saveNewCheck(@RequestBody @Valid CheckPackDTO checkPackDTO) {
        checkService.save(checkPackDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
