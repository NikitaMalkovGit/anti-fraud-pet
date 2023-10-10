package com.pet.antifraud.DTO;

import com.pet.antifraud.Enum.TransactionAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
    private TransactionAction result;
    private String info;
}
