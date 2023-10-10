package com.pet.antifraud.DTO;

import lombok.Data;

import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TransactionDTO {

    @NotNull
    private Long amount;

    @NotNull
    private String ip;

    @NotNull
    private String number;

    @NotNull
    private String region;

    @NotNull
    private String date;
}
