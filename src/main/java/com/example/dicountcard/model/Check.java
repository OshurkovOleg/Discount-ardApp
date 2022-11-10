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
@Table(name = "check_user")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "check_number")
    private long checkNumber;

    @Column(name = "number_card")
    private int numberCard;

    @Column(name = "total")
    private int total;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "check")
    private List<PositionFromCheck> positionFromCheckList;


    public Check() {
    }

    public Check(long checkID, int numberCard, int total) {
        this.checkNumber = checkID;
        this.numberCard = numberCard;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Check{" + "checkID=" + checkNumber + ", numberCard=" + numberCard + ", total=" + total + '}';
    }
}
