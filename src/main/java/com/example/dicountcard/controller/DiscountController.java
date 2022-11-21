package com.example.dicountcard.controller;

import com.example.dicountcard.dto.CheckDTO;
import com.example.dicountcard.model.Check;
import com.example.dicountcard.model.Client;
import com.example.dicountcard.model.PositionFromCheck;
import com.example.dicountcard.services.CheckService;
import com.example.dicountcard.services.ClientService;
import com.example.dicountcard.services.PositionFromCheckService;
import com.example.dicountcard.util.CardNumberValueInvalidException;
import com.example.dicountcard.util.CheckNotCreatedException;
import com.example.dicountcard.util.ClientErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.dicountcard.constants.Constants.THE_CARD_NUMBER_CANNOT_BE_NEGATIVE;

@RestController
@RequestMapping("/api")
public class DiscountController {
    ClientService clientService;
    CheckService checkService;

    PositionFromCheckService positionFromCheckService;

    @Autowired
    public DiscountController(ClientService clientService, CheckService checkService, PositionFromCheckService positionFromCheckService) {
        this.clientService = clientService;
        this.checkService = checkService;
        this.positionFromCheckService = positionFromCheckService;
    }

    @GetMapping("clients/all")
    public List<Client> getAllCardClients() {
        return clientService.getAll();
    }

    @GetMapping("clients/{card}")
    public Client getOrAddClientCard(@PathVariable long card) {
        return clientService.get(card);
    }

    @PostMapping("checks")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CheckDTO checkDTO,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new CheckNotCreatedException(errorMsg.toString());
        }

        Check check = convertToCheck(checkDTO);
        checkService.save(check);
        putPositionFromCheck(checkDTO);

// возвращаем положительный ответ если ошибок не было
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private void putPositionFromCheck(CheckDTO checkDTO) {
        List<PositionFromCheck> position = checkDTO.getPosition();
        Check checkForPosition = checkService.get(checkDTO.getCheckNumber());

        for (PositionFromCheck positionFromCheck : position) {
            positionFromCheck.setCheck(checkForPosition);
            positionFromCheckService.save(positionFromCheck);
        }
    }

    private Check convertToCheck(CheckDTO checkDTO) {
        Client client = clientService.get(checkDTO.getCardNumber());

        Check check = new Check();
        check.setCheckNumber(checkDTO.getCheckNumber());
        check.setTotal(checkDTO.getTotal());
        check.setClient(client);
        return check;
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handlerException(CardNumberValueInvalidException e) {
        ClientErrorResponse response = new ClientErrorResponse(THE_CARD_NUMBER_CANNOT_BE_NEGATIVE);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handlerException(CheckNotCreatedException e) {
        ClientErrorResponse response = new ClientErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
