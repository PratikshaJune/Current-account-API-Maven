package com.example.accountservice.service;

import com.example.accountservice.client.TransactionClient;
import com.example.common.dto.CustomerDTO;
import com.example.common.dto.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @MockBean
    private TransactionClient transactionClient;

    @Test
    public void testGetCustomerInfo() {
        Long customerId = 1L;

        accountService.createAccount(customerId, 100.0);
        accountService.createAccount(customerId, 200.0);

        when(transactionClient.getTransactions(anyLong())).thenReturn(List.of(
                new TransactionDTO(1L, 1L, 100.0, null),
                new TransactionDTO(2L, 2L, 200.0, null)
        ));

        CustomerDTO customerInfo = customerService.getCustomerInfo(customerId);

        assertThat(customerInfo).isNotNull();
        assertThat(customerInfo.getCustomerId()).isEqualTo(customerId);
        assertThat(customerInfo.getAccounts()).hasSize(2);
    }
}
