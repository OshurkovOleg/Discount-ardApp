package com.example.dicountcard.services;

import com.example.dicountcard.model.Client;
import com.example.dicountcard.repository.ClientRepository;
import com.example.dicountcard.util.CardNumberValueInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.dicountcard.constants.Constants.CHECKING_THE_CARD_IN_THE_CLIENT_SERVICE;


@Service
public class ClientService {
    ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() {
        return clientRepository.getAllBy();
    }

    public Client get(long card) {

        if (card < 1) {
            throw new CardNumberValueInvalidException(CHECKING_THE_CARD_IN_THE_CLIENT_SERVICE);
        }

        if (clientRepository.getClientByCardNumber(card) == null) {
            clientRepository.save(new Client(card, 0));
        }

        return clientRepository.getClientByCardNumber(card);
    }

    @Transactional
    public void save(long card) {
        clientRepository.save(new Client(card, 0));
    }
}


