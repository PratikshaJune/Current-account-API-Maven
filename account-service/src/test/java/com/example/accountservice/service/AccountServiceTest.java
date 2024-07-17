package com.example.accountservice.service;

import com.example.accountservice.client.TransactionClient;
import com.example.accountservice.model.Account;
import com.example.accountservice.repository.AccountRepository;
import com.example.common.dto.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @MockBean
    private TransactionClient transactionClient;

    @Test
    public void testCreateAccountWithInitialCredit() {
        doNothing().when(transactionClient).createTransaction(any(TransactionDTO.class));

        Long customerId = 1L;
        double initialCredit = 100.0;

        Account account = accountService.createAccount(customerId, initialCredit);

        assertThat(account).isNotNull();
        assertThat(account.getCustomerId()).isEqualTo(customerId);
        assertThat(account.getBalance()).isEqualTo(initialCredit);

        ArgumentCaptor<TransactionDTO> transactionCaptor = ArgumentCaptor.forClass(TransactionDTO.class);
        verify(transactionClient, times(1)).createTransaction(transactionCaptor.capture());

        TransactionDTO transaction = transactionCaptor.getValue();
        assertThat(transaction).isNotNull();
        assertThat(transaction.getAccountId()).isEqualTo(account.getId());
        assertThat(transaction.getAmount()).isEqualTo(initialCredit);
    }

    @Test
    public void testCreateAccountWithoutInitialCredit() {
        Long customerId = 2L;
        double initialCredit = 0.0;

        Account account = accountService.createAccount(customerId, initialCredit);

        assertThat(account).isNotNull();
        assertThat(account.getCustomerId()).isEqualTo(customerId);
        assertThat(account.getBalance()).isEqualTo(initialCredit);

        verify(transactionClient, times(0)).createTransaction(any(TransactionDTO.class));
    }

    @Test
    public void testGetAccountsForCustomer() {
        Long customerId = 1L;
        Account account1 = new Account(customerId);
        account1.setBalance(100.0);
        Account account2 = new Account(customerId);
        account2.setBalance(200.0);

        accountRepository.saveAll(List.of(account1, account2));

        List<Account> accounts = accountService.getAccountsForCustomer(customerId);

        assertThat(accounts).hasSize(2);
    }
}
