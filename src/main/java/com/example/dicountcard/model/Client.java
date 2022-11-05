package com.example.dicountcard.model;

//номер карты (20 цифр), количество доступных баллов.

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "number_card")
    private int numberCard;

    @Column(name = "balance_card")
    private int balanceCard;

    public Client() {
    }

    public Client(int numberCard, int balanceCard) {
        this.numberCard = numberCard;
        this.balanceCard = balanceCard;
    }

    @Override
    public String toString() {
        return "Client{" +
                "numberCard=" + numberCard +
                ", balanceCard=" + balanceCard +
                '}';
    }
}
