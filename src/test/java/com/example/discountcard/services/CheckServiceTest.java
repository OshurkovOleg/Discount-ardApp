package com.example.discountcard.services;

import com.example.discountcard.dto.CheckDTO;
import com.example.discountcard.entities.ClientEntity;
import com.example.discountcard.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CheckServiceTest {

    @Mock
    ClientRepository clientRepository;

    @Test
    void shouldSaveClientBaseAndGetClient() {
        CheckDTO checkDTO = new CheckDTO();
        checkDTO.setNumber(3);
        checkDTO.setPrice(3000);
        checkDTO.setCardNumber(423111121);
        checkDTO.setListPosition(new ArrayList<>());

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(42);
        clientEntity.setCardBalance(2000);
        clientEntity.setCardNumber(423111121);
        clientEntity.setCheckEntities(new ArrayList<>());

        Mockito.when(clientRepository.save(Mockito.any(ClientEntity.class))).thenReturn(clientEntity);

        ClientEntity result = clientRepository.save(clientEntity);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getCheckEntities());
        Assertions.assertEquals(checkDTO.getCardNumber(), clientEntity.getCardNumber());
    }

}
