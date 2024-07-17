package com.example.accountservice.model;

import com.example.common.dto.TransactionDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private double balance;

    @Transient
    private List<TransactionDTO> transactions;

    public Account() {
    }

    public Account(Long customerId) {
        this.customerId = customerId;
        this.balance = 0;
    }
}
