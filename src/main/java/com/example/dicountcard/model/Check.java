package com.example.dicountcard.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "check_user")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "check_id")
    private long checkID;

    @Column(name = "number_card")
    private int numberCard;

    @Column(name = "total")
    private int total;

    public Check() {
    }

    public Check(long checkID, int numberCard, int total) {
        this.checkID = checkID;
        this.numberCard = numberCard;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Check{" +
                "checkID=" + checkID +
                ", numberCard=" + numberCard +
                ", total=" + total +
                '}';
    }
}
