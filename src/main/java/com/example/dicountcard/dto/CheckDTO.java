package com.example.dicountcard.dto;

import com.example.dicountcard.model.PositionFromCheck;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CheckDTO {

    private long checkNumber;

    private long cardNumber;

    private long total;

    private List<PositionFromCheck> listPosition;

    public CheckDTO(long checkNumber, long cardNumber, long total, List<PositionFromCheck> listPosition) {
        this.checkNumber = checkNumber;
        this.cardNumber = cardNumber;
        this.total = total;
        this.listPosition = listPosition;
    }
}
