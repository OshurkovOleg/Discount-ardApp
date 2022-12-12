package com.example.dicountcard.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

    private long bill;
    private long cardNumber;


    public ClientDTO(long bill, long cardNumber) {
        this.bill = bill;
        this.cardNumber = cardNumber;

    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "bill=" + bill +
                ", cardNumber=" + cardNumber +
                '}';
    }
}
