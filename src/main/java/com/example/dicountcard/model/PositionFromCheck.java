package com.example.dicountcard.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "check_position")
public class PositionFromCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "position_amount")
    private long positionAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "check_id", referencedColumnName = "id")
    private Check check;

    public PositionFromCheck(long positionAmount, Check check) {
        this.positionAmount = positionAmount;
        this.check = check;
    }

    @Override
    public String toString() {
        return "PositionFromCheck{" +
                "positionAmount=" + positionAmount +
                ", check=" + check +
                '}';
    }
}
