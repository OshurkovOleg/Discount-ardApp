package com.example.dicountcard.repository;

import com.example.dicountcard.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    public List<Client> getAllBy();
    public Client getClientByCardNumber(long numberCard);
}
