package com.example.dicountcard.repository;

import com.example.dicountcard.model.PositionFromCheck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionFromCheckRepository extends CrudRepository<PositionFromCheck, Long> {

}
