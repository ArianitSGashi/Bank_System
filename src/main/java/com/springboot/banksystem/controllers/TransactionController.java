package com.springboot.banksystem.controllers;

import com.springboot.banksystem.dtos.TransactionDTO;
import com.springboot.banksystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public TransactionDTO performTransaction(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.performTransaction(transactionDTO);
    }

    @GetMapping("/account/{accountId}")
    public List<TransactionDTO> getTransactionsByAccountId(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }
}

