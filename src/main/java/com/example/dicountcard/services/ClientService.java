package com.example.dicountcard.services;

import com.example.dicountcard.exceptions.CardNotFoundException;
import com.example.dicountcard.entities.ClientEntity;
import com.example.dicountcard.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientEntity> getAll() {
        ArrayList<ClientEntity> clientEntities = new ArrayList<>();
        clientRepository.findAll()
                .forEach(clientEntities::add);
        return clientEntities;
    }

    public ClientEntity getByCardNumber(long card) {

        if (clientRepository.getClientByCardNumber(card) == null) {
            throw new CardNotFoundException("ClientService.get didn't exist");
        }
        return clientRepository.getClientByCardNumber(card);
    }

    @Transactional
    public void save(long card) {
        clientRepository.save(new ClientEntity(card, 0));
    }

}


