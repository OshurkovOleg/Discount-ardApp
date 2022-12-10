package com.example.dicountcard.dto;

import com.example.dicountcard.model.Check;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ClientDTO {
    private long cardNumber;
    private long cardBalance;
    public ClientDTO(long cardNumber, long cardBalance) {
        this.cardNumber = cardNumber;
        this.cardBalance = cardBalance;

    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "cardNumber=" + cardNumber +
                ", cardBalance=" + cardBalance +
                '}';
    }
}
