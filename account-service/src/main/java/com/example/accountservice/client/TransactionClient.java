package com.example.accountservice.client;

import com.example.common.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "transaction-service")
public interface TransactionClient {

    @PostMapping("/transactions")
    void createTransaction(@RequestBody TransactionDTO transaction);

    @GetMapping("/transactions/{accountId}")
    List<TransactionDTO> getTransactions(@PathVariable Long accountId);
}
