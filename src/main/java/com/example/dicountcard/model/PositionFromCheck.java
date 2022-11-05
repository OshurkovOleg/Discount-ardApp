package com.example.dicountcard.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "position_from_check")
public class PositionFromCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "check_id")
    private long CheckID;

    @Column(name = "position_amount")
    private int positionAmount;

    public PositionFromCheck() {
    }

    public PositionFromCheck(long checkID, int positionAmount) {
        CheckID = checkID;
        this.positionAmount = positionAmount;
    }

    @Override
    public String toString() {
        return "PositionFromCheck{" +
                "CheckID=" + CheckID +
                ", positionAmount=" + positionAmount +
                '}';
    }
}
