package com.example.transactionservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private double amount;
    private Date date;

    public Transaction() {
        this.date = new Date();
    }

    public Transaction(Long accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
        this.date = new Date();
    }
}
