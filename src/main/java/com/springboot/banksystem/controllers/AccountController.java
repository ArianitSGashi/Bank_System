package com.springboot.banksystem.controllers;


import com.springboot.banksystem.dtos.AccountDTO;
import com.springboot.banksystem.dtos.TransactionDTO;
import com.springboot.banksystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public AccountDTO createAccount(@RequestBody AccountDTO accountDTO) {
        if (accountDTO.getBank() == null || accountDTO.getBank().getId() == null) {
            throw new IllegalArgumentException("Bank information is missing");
        }
        return accountService.createAccount(accountDTO);
    }

    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping()
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/withdraw")
    public AccountDTO withdraw(@RequestBody TransactionDTO transactionDTO) {
        return accountService.withdraw(transactionDTO);
    }

    @PostMapping("/deposit")
    public AccountDTO deposit(@RequestBody TransactionDTO transactionDTO) {
        return accountService.deposit(transactionDTO);
    }
}
