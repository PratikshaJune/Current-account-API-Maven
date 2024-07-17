package com.example.frontend.controller;

import com.example.common.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@EnableFeignClients
public class IndexController {

    private final AccountClient accountClient;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/customer")
    public String getCustomerInfo(@RequestParam Long customerId, Model model) {
        CustomerDTO customer = accountClient.getCustomerInfo(customerId);
        model.addAttribute("customer", customer);
        return "customer-info";
    }

    @FeignClient(name = "account-service", url = "http://api-gateway:8765/accounts")
    interface AccountClient {
        @GetMapping("/{customerId}")
        CustomerDTO getCustomerInfo(@PathVariable("customerId") Long customerId);
    }
}
