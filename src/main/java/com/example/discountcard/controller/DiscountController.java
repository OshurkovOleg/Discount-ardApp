package com.example.discountcard.controller;

import com.example.discountcard.dto.CheckPackDTO;
import com.example.discountcard.entities.ClientEntity;
import com.example.discountcard.services.CheckService;
import com.example.discountcard.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(description = "Главный контроллер")
public class DiscountController {

    private final ClientService clientService;
    private final CheckService checkService;


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
    public ResponseEntity<HttpStatus> saveNewCheck(@RequestBody CheckPackDTO checkPackDTO) {
        checkService.save(checkPackDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
