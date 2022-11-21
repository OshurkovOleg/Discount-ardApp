package com.example.dicountcard.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "card_number", length = 20)
    private long cardNumber;

    @Column(name = "card_balance")
    private long cardBalance;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private List<Check> checks;

    public Client() {
    }

    public Client(long numberCard, long cardBalance) {
        this.cardNumber = numberCard;
        this.cardBalance = cardBalance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "numberCard=" + cardNumber +
                ", balanceCard=" + cardBalance +
                '}';
    }
}