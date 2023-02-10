package com.example.discountcard.services;

import com.example.discountcard.entities.CheckEntity;
import com.example.discountcard.entities.ClientEntity;
import com.example.discountcard.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    ClientService clientService;

    //TODO лишний
    @Test
    void shouldReturnAllClients() {
        ArrayList<ClientEntity> clients = createClients();

        Mockito.when(clientRepository.findAll())
                .thenReturn(clients);

        List<ClientEntity> result = clientService.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(clients.get(1), result.get(1));
    }

    @Test
    void shouldReturnCardFilteredByCardNumber() {
        ArrayList<ClientEntity> clients = createClients();
        int card = 101200310;

        Mockito.when(clientRepository.getClientByCardNumber(card))
                .thenReturn(clients.get(0));

        ClientEntity result = clientService.getByCardNumber(card);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(card, result.getCardNumber());
    }


    private ArrayList<ClientEntity> createClients() {

        List<CheckEntity> checks = new ArrayList<>();

        ClientEntity firstClient = new ClientEntity();
        ClientEntity secondClient = new ClientEntity();

        ArrayList<ClientEntity> clientEntities = new ArrayList<>();

        firstClient.setId(5);
        firstClient.setCardNumber(101200310);
        firstClient.setCardBalance(1000);
        firstClient.setCheckEntities(checks);

        secondClient.setId(6);
        secondClient.setCardNumber(203200510);
        secondClient.setCardBalance(2000);
        secondClient.setCheckEntities(checks);

        clientEntities.add(firstClient);
        clientEntities.add(secondClient);

        return clientEntities;
    }


}
