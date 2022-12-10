package com.example.dicountcard.services;

import com.example.dicountcard.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckPackService {

    CheckRepository checkRepository;

    @Autowired
    public CheckPackService(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }




}
