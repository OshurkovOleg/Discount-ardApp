package com.example.discountcard.dto;

import com.example.discountcard.entities.PositionFromCheckEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CheckDTO {

    //TODO  @NonNull Valid
//    @NotNull
    private Long number;
    private long cardNumber;
    private long price;
    private List<PositionFromCheckEntity> listPosition;

    public CheckDTO(long number, long cardNumber, long price, List<PositionFromCheckEntity> listPosition) {
        this.number = number;
        this.cardNumber = cardNumber;
        this.price = price;
        this.listPosition = listPosition;
    }

    @Override
    public String toString() {
        return "CheckDTO{" +
                "checkNumber=" + number +
                ", cardNumber=" + cardNumber +
                ", checkAmount=" + price +
                ", listPosition=" + listPosition +
                '}';
    }

}
