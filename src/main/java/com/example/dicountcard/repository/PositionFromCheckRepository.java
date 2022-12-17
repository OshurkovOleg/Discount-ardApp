package com.example.dicountcard.repository;

import com.example.dicountcard.entities.PositionFromCheckEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionFromCheckRepository extends CrudRepository<PositionFromCheckEntity, Long> {

}
