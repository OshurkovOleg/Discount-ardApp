package com.example.dicountcard.dto;

import com.example.dicountcard.model.Check;
import lombok.Data;

import java.util.List;

@Data
public class CheckPackDTO {
    public List<Check> checkList;

    public CheckPackDTO(List<Check> checkList) {
        this.checkList = checkList;
    }

    @Override
    public String toString() {
        return "CheckPackDTO{" +
                "checkList=" + checkList +
                '}';
    }
}
