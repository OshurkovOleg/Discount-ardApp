package com.example.dicountcard.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "position_from_check")
public class PositionFromCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;


    @Column(name = "position_amount")
    private long positionAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "check_number", referencedColumnName = "check_number")
    private Check check;


    public PositionFromCheck() {
    }

    public PositionFromCheck(long positionAmount, Check check) {
        this.positionAmount = positionAmount;
        this.check = check;
    }
}
