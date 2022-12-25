package com.example.discountcard.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ClientDTO {

    private long balance;
    private long cardNumber;

    public ClientDTO(long balance, long cardNumber) {
        this.balance = balance;
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "account=" + balance +
                ", cardNumber=" + cardNumber +
                '}';
    }
}
