package com.example.accountservice.controller;

import com.example.common.dto.CustomerDTO;
import com.example.accountservice.model.Account;
import com.example.accountservice.service.AccountService;
import com.example.accountservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    @PostMapping
    public Account createAccount(@RequestParam Long customerId, @RequestParam double initialCredit) {
        return accountService.createAccount(customerId, initialCredit);
    }

    @GetMapping("/{customerId}")
    public CustomerDTO getCustomerInfo(@PathVariable Long customerId) {
        return customerService.getCustomerInfo(customerId);
    }
}
