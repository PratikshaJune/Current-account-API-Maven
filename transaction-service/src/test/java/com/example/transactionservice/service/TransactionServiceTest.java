package com.example.transactionservice.service;

import com.example.transactionservice.model.Transaction;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.common.dto.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    public void testCreateTransaction() {
        TransactionDTO transactionDTO = new TransactionDTO(null, 1L, 100.0, null);
        Transaction transaction = new Transaction(1L, 100.0);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        transactionService.createTransaction(transaction);

        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepository, times(1)).save(transactionCaptor.capture());

        Transaction savedTransaction = transactionCaptor.getValue();
        assertThat(savedTransaction).isNotNull();
        assertThat(savedTransaction.getAccountId()).isEqualTo(transactionDTO.getAccountId());
        assertThat(savedTransaction.getAmount()).isEqualTo(transactionDTO.getAmount());
    }

    @Test
    public void testGetTransactions() {
        Long accountId = 1L;
        Transaction transaction1 = new Transaction(accountId, 100.0);
        Transaction transaction2 = new Transaction(accountId, 200.0);

        when(transactionRepository.findByAccountId(accountId)).thenReturn(List.of(transaction1, transaction2));

        List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);

        assertThat(transactions).hasSize(2);
        assertThat(transactions.get(0).getAmount()).isEqualTo(100.0);
        assertThat(transactions.get(1).getAmount()).isEqualTo(200.0);
    }
}
