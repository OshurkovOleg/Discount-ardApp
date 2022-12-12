package com.example.dicountcard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

    private long account;
    private long cardNumber;

    public ClientDTO(long bill, long cardNumber) {
        this.account = bill;
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "account=" + account +
                ", cardNumber=" + cardNumber +
                '}';
    }
}
