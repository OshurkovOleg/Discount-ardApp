package com.example.dicountcard.mappers;

import com.example.dicountcard.dto.CheckDTO;
import com.example.dicountcard.dto.ClientDTO;
import com.example.dicountcard.entities.CheckEntity;
import com.example.dicountcard.entities.ClientEntity;
import com.example.dicountcard.entities.PositionFromCheckEntity;
import com.example.dicountcard.repository.CheckRepository;
import com.example.dicountcard.services.PositionFromCheckService;
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


}


