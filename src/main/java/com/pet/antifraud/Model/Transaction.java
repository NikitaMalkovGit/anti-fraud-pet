package com.pet.antifraud.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pet.antifraud.DTO.TransactionDTO;
import com.pet.antifraud.Enum.Region;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSACTION_HISTORY")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotNull
    private Long amount;

    @NotNull
    private String ip;

    @NotNull
    private String number;

    @NotNull
    private Region region;

    @NotNull
    private LocalDateTime date;

    /**
     * Constructor to create a Transaction entity from a TransactionDTO.
     * @param transactionDTO The TransactionDTO to create the entity from.
     */
    public Transaction(TransactionDTO transactionDTO) {
        this.amount = transactionDTO.getAmount();
        this.number = transactionDTO.getNumber();
        this.ip = transactionDTO.getIp();
        this.region = Region.valueOf(transactionDTO.getRegion());
        this.date = LocalDateTime.parse(transactionDTO.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }
}
