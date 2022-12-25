package com.example.discountcard.repository;

import com.example.discountcard.entities.PositionFromCheckEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionFromCheckRepository extends CrudRepository<PositionFromCheckEntity, Long> {

}
