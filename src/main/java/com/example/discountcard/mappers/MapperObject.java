package com.example.discountcard.mappers;

import com.example.discountcard.dto.CheckDTO;
import com.example.discountcard.entities.CheckEntity;
import com.example.discountcard.entities.ClientEntity;
import com.example.discountcard.repository.CheckRepository;
import com.example.discountcard.services.PositionFromCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperObject {

    public final CheckRepository checkRepository;
    public final PositionFromCheckService positionFromCheckService;

    @Autowired
    public MapperObject(CheckRepository checkRepository, PositionFromCheckService positionFromCheckService) {
        this.checkRepository = checkRepository;
        this.positionFromCheckService = positionFromCheckService;
    }

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


