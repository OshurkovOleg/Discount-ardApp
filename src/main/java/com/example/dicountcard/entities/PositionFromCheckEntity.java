package com.example.dicountcard.entities;

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
public class PositionFromCheckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "price")
    private long price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "check_id", referencedColumnName = "id")
    private CheckEntity checkEntity;

    public PositionFromCheckEntity(long price, CheckEntity checkEntity) {
        this.price = price;
        this.checkEntity = checkEntity;
    }

    @Override
    public String toString() {
        return "PositionFromCheck{" +
                "positionAmount=" + price +
                ", check=" + checkEntity +
                '}';
    }
}
