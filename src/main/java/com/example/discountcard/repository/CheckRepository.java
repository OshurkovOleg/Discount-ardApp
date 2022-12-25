package com.example.discountcard.repository;

import com.example.discountcard.entities.CheckEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckRepository extends CrudRepository<CheckEntity, Long> {

}
