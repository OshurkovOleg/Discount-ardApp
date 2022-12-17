package com.example.dicountcard.services;

import com.example.dicountcard.entities.PositionFromCheckEntity;
import com.example.dicountcard.repository.PositionFromCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionFromCheckService {

    private final PositionFromCheckRepository positionFromCheckRepository;

    @Autowired
    public PositionFromCheckService(PositionFromCheckRepository positionFromCheckRepository) {
        this.positionFromCheckRepository = positionFromCheckRepository;
    }

    public void save(PositionFromCheckEntity positionFromCheckEntity) {
        positionFromCheckRepository.save(positionFromCheckEntity);
    }

}
