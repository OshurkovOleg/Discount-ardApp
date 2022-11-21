package com.example.dicountcard.services;

import com.example.dicountcard.model.Check;
import com.example.dicountcard.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckService {

    CheckRepository checkRepository;

    @Autowired
    public CheckService(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    @Transactional
    public void save(Check check) {
        checkRepository.save(check);
    }

    public Check get(long checkNumber){
        return checkRepository.getCheckByCheckNumber(checkNumber);
    }

}
