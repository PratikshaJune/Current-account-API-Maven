package com.example.accountservice.service;

import com.example.accountservice.client.TransactionClient;
import com.example.accountservice.model.Account;
import com.example.common.dto.CustomerDTO;
import com.example.common.dto.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final AccountService accountService;
    private final TransactionClient transactionClient;

    public CustomerDTO getCustomerInfo(Long customerId) {
        List<Account> accounts = accountService.getAccountsForCustomer(customerId);
        accounts.forEach(account -> {
            account.setTransactions(transactionClient.getTransactions(account.getId()));
        });

        List<AccountDTO> accountDTOs = accounts.stream()
                .map(account -> new AccountDTO(account.getId(), account.getCustomerId(), account.getBalance(), account.getTransactions()))
                .collect(Collectors.toList());

        return new CustomerDTO(
                customerId,
                "John",
                "Doe",
                accountDTOs
        );
    }
}
