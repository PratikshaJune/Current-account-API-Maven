package com.example.accountservice.service;

import com.example.accountservice.client.TransactionClient;
import com.example.accountservice.model.Account;
import com.example.accountservice.repository.AccountRepository;
import com.example.common.dto.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionClient transactionClient;

    @Transactional
    public Account createAccount(Long customerId, double initialCredit) {
        Account account = new Account(customerId);
        accountRepository.save(account);

        if (initialCredit > 0) {
            TransactionDTO transaction = new TransactionDTO(null, account.getId(), initialCredit, null);
            transactionClient.createTransaction(transaction);
            account.setBalance(initialCredit);
            accountRepository.save(account);
        }

        return account;
    }

    public List<Account> getAccountsForCustomer(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }
}
