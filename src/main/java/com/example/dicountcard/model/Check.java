package com.example.dicountcard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "client_check")
public class Check {

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "check")
    private List<PositionFromCheck> positionFromCheckList;


    public Check(long checkNumber, long total, Client client) {
        this.checkNumber = checkNumber;
        this.total = total;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Check{" +
                "checkNumber=" + checkNumber +
                ", total=" + total +
                ", client=" + client +
                ", positionFromCheckList=" + positionFromCheckList +
                '}';
    }
}
