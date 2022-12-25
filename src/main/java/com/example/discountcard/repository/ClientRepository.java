package com.example.discountcard.repository;

import com.example.discountcard.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
    ClientEntity getClientByCardNumber(long numberCard);
}
