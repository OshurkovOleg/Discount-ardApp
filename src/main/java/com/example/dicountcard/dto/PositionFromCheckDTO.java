package com.example.dicountcard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionFromCheckDTO {
    private long positionAmount;

    public PositionFromCheckDTO(long positionAmount) {
        this.positionAmount = positionAmount;
    }

    @Override
    public String toString() {
        return "PositionFromCheckDTO{" +
                "positionAmount=" + positionAmount +
                '}';
    }
}
