package com.example.discountcard.mappers;

import com.example.discountcard.dto.CheckDTO;
import com.example.discountcard.entities.CheckEntity;
import com.example.discountcard.entities.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperObject {

    public static CheckEntity getCheckEntityFromCheckDTO(CheckDTO checkDTO, ClientEntity clientEntity) {

        CheckEntity checkEntity = new CheckEntity();

        checkEntity.setNumber(checkDTO.getNumber());
        checkEntity.setPrice(checkDTO.getPrice());
        checkEntity.setClientEntity(clientEntity);

        return checkEntity;
    }

    public static ClientEntity getNewClientEntityUsingCardNumberFromCheckDTO(CheckDTO checkDTO) {
        return new ClientEntity(checkDTO.getCardNumber(), 0);
    }
}
