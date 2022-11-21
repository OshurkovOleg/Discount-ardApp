package com.example.dicountcard.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "client_check")
public class Check implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "check_number")
    @NotEmpty(message = "Number check should not by empty")
    @Min(value = 1, message = "Number check should not by less one")
    private long checkNumber;

    @Column(name = "total")
    @NotEmpty(message = "Total should not by empty")
    @Min(value = 1, message = "Total should not by less one")
    private long total;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "card_number", referencedColumnName = "card_number")
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "check")
    private List<PositionFromCheck> positionFromCheckList;

    public Check() {
    }

    public Check(long checkNumber, long total, Client client) {
        this.checkNumber = checkNumber;
        this.total = total;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Check{" + "checkID=" + checkNumber + ", numberCard=" + ", total=" + total + '}';
    }
}
