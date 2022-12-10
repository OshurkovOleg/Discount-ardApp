package com.example.dicountcard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "card_number", length = 20)
    private long cardNumber;

    @Column(name = "card_balance")
    private long cardBalance;
    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private List<Check> checks;

    public Client(long numberCard, long cardBalance) {
        this.cardNumber = numberCard;
        this.cardBalance = cardBalance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "cardNumber=" + cardNumber +
                ", cardBalance=" + cardBalance +
                ", checks=" + checks +
                '}';
    }
}