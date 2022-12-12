package com.example.dicountcard.dto;

import com.example.dicountcard.model.PositionFromCheck;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CheckDTO {

    private long checkNumber;
    private long cardNumber;
    private long checkAmount;
    private List<PositionFromCheck> listPosition;

    public CheckDTO(long checkNumber, long cardNumber, long total, List<PositionFromCheck> listPosition) {
        this.checkNumber = checkNumber;
        this.cardNumber = cardNumber;
        this.checkAmount = total;
        this.listPosition = listPosition;
    }

    @Override
    public String toString() {
        return "CheckDTO{" +
                "checkNumber=" + checkNumber +
                ", cardNumber=" + cardNumber +
                ", checkAmount=" + checkAmount +
                ", listPosition=" + listPosition +
                '}';
    }
}
