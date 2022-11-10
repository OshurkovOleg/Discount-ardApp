package com.example.dicountcard.services;

import com.example.dicountcard.model.Client;
import com.example.dicountcard.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

}
