package com.example.dicountcard.repository;

import com.example.dicountcard.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
    ClientEntity getClientByCardNumber(long numberCard);
}
