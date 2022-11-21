package com.example.dicountcard.services;

import com.example.dicountcard.model.PositionFromCheck;
import com.example.dicountcard.repository.PositionFromCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionFromCheckService {

    PositionFromCheckRepository positionFromCheckRepository;

    @Autowired
    public PositionFromCheckService(PositionFromCheckRepository positionFromCheckRepository) {
        this.positionFromCheckRepository = positionFromCheckRepository;
    }

    public void save(PositionFromCheck positionFromCheck) {
        positionFromCheckRepository.save(positionFromCheck);
    }

}
