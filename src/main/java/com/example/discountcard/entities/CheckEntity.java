package com.example.discountcard.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "client_check")
public class CheckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "number")
    @NotEmpty(message = "Number check should not by empty")
    @Min(value = 1, message = "Number check should not by less one")
    private long number;

    @Column(name = "price")
    @NotEmpty(message = "Total should not by empty")
    @Min(value = 1, message = "Total should not by less one")
    private long price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity clientEntity;


    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "checkEntity")
    private List<PositionFromCheckEntity> positionFromCheckEntityList;

    public CheckEntity(long number, long price, ClientEntity clientEntity) {
        this.number = number;
        this.price = price;
        this.clientEntity = clientEntity;
    }

    @Override
    public String toString() {
        return "Check{" +
                "checkNumber=" + number +
                ", total=" + price +
                ", client=" + clientEntity +
                ", positionFromCheckList=" + positionFromCheckEntityList +
                '}';
    }
}
