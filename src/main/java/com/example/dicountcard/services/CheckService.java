package com.example.dicountcard.services;

import com.example.dicountcard.dto.CheckDTO;
import com.example.dicountcard.model.Check;
import com.example.dicountcard.model.Client;
import com.example.dicountcard.repository.CheckRepository;
import com.example.dicountcard.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckService {

    private final CheckRepository checkRepository;
    private final ClientRepository clientRepository;
    private final PositionFromCheckService positionFromCheckService;

    @Autowired
    public CheckService(CheckRepository checkRepository, ClientRepository clientRepository,
                        PositionFromCheckService positionFromCheckService) {
        this.checkRepository = checkRepository;
        this.clientRepository = clientRepository;
        this.positionFromCheckService = positionFromCheckService;
    }

    public void save(CheckDTO checkDTO) {

        long cardNumber = checkDTO.getCardNumber();
        Client client = clientRepository.getClientByCardNumber(cardNumber);

        if (client == null) {
            Client savedClient = clientRepository.save(new Client(checkDTO.getCardNumber(), 0));

            Check savedCheck = checkRepository.save(new Check(checkDTO.getCheckNumber(), checkDTO.getTotal(),
                    savedClient));

            checkDTO.getListPosition().stream()
                    .peek(e -> e.setCheck(savedCheck))
                    .forEach(positionFromCheckService::save);
        } else {

            Check savedCheck = checkRepository.save(new Check(checkDTO.getCheckNumber(), checkDTO.getTotal(),
                    client));

            checkDTO.getListPosition().stream()
                    .peek(e -> e.setCheck(savedCheck))
                    .forEach(positionFromCheckService::save);
        }
    }


}
