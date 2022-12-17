package com.example.dicountcard.entities;

import com.example.dicountcard.dto.CheckDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "card_number", length = 20)
    private long cardNumber;

    @Column(name = "card_balance")
    private long cardBalance;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "clientEntity")
    private List<CheckEntity> checkEntities;

    public ClientEntity(long numberCard, long cardBalance) {
        this.cardNumber = numberCard;
        this.cardBalance = cardBalance;
    }

    public static ClientEntity getNewClientEntityUsingCardNumberFromCheckDTO(CheckDTO checkDTO) {
        return new ClientEntity(checkDTO.getCardNumber(), 0);
    }

    @Override
    public String toString() {
        return "Client{" +
                "cardNumber=" + cardNumber +
                ", cardBalance=" + cardBalance +
                ", checks=" + checkEntities +
                '}';
    }
}