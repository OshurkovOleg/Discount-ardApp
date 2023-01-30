package com.example.discountcard.mappers;

import com.example.discountcard.Application;
import com.example.discountcard.dto.CheckDTO;
import com.example.discountcard.entities.CheckEntity;
import com.example.discountcard.entities.ClientEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;


@SpringBootTest(classes = Application.class)
public class MapperObjectTest {


    @Test
    void shouldGetCheckEntityFromCheckDTO() {

        CheckDTO checkDTO = new CheckDTO();
        checkDTO.setNumber(1);
        checkDTO.setPrice(1000);
        checkDTO.setCardNumber(923472100);
        checkDTO.setListPosition(new ArrayList<>());


        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(20);
        clientEntity.setCardBalance(2000);
        clientEntity.setCardNumber(923472100);
        clientEntity.setCheckEntities(new ArrayList<>());

        CheckEntity result = MapperObject.getCheckEntityFromCheckDTO(checkDTO, clientEntity);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getClientEntity());
        Assertions.assertEquals(checkDTO.getPrice(), result.getPrice());
        Assertions.assertEquals(checkDTO.getNumber(), result.getNumber());


    }

    @Test
    void shouldGetNewClientEntityUsingCardNumberFromCheckDTO() {

        CheckDTO checkDTO = new CheckDTO();
        checkDTO.setNumber(2);
        checkDTO.setPrice(2000);
        checkDTO.setCardNumber(223498122);
        checkDTO.setListPosition(new ArrayList<>());

        ClientEntity result = MapperObject.getNewClientEntityUsingCardNumberFromCheckDTO(checkDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.getCardBalance());
        Assertions.assertEquals(checkDTO.getCardNumber(), result.getCardNumber());
    }
}
