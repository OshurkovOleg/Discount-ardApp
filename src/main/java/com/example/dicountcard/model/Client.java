package com.example.dicountcard.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "number_card", length = 20)
    private long numberCard;

    @Column(name = "balance_card")
    private long balanceCard;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private List<Check> checks;

    public Client() {
    }

    public Client(long numberCard, long balanceCard) {
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