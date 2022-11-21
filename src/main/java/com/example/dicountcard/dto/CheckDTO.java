package com.example.dicountcard.dto;

import com.example.dicountcard.model.PositionFromCheck;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CheckDTO {

    @NotEmpty(message = "Number check should not by empty")
    @Min(value = 1, message = "Number check should not by less one")
    private long checkNumber;

    @NotEmpty(message = "Number card should not by empty")
    @Min(value = 1, message = "Number card should not by less one")
    private long cardNumber;

    @NotEmpty(message = "Total should not by empty")
    @Min(value = 1, message = "Total should not by less one")
    private long total;

    private List<PositionFromCheck> position;

    public CheckDTO(long checkNumber, long cardNumber, long total) {
        this.checkNumber = checkNumber;
        this.cardNumber = cardNumber;
        this.total = total;
    }
}
