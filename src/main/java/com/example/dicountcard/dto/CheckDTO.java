package com.example.dicountcard.dto;

import com.example.dicountcard.model.PositionFromCheck;
import lombok.Data;
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

    private long total;

    private List<PositionFromCheck> listPosition;

    public CheckDTO(long checkNumber, long cardNumber, long total, List<PositionFromCheck> listPosition) {
        this.checkNumber = checkNumber;
        this.cardNumber = cardNumber;
        this.total = total;
        this.listPosition = listPosition;
    }
}
