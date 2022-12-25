package com.example.discountcard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CheckPackDTO {

    public List<CheckDTO> checkList;

    public CheckPackDTO(List<CheckDTO> checkList) {
        this.checkList = checkList;
    }

    @Override
    public String toString() {
        return "CheckPackDTO{" +
                "checkList=" + checkList +
                '}';
    }
}
