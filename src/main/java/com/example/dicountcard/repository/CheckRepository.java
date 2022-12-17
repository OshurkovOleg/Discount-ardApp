package com.example.dicountcard.repository;

import com.example.dicountcard.entities.CheckEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckRepository extends CrudRepository<CheckEntity, Long> {

}
