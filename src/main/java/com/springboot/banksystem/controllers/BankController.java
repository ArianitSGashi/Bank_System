package com.springboot.banksystem.controllers;

import com.springboot.banksystem.dtos.AccountDTO;
import com.springboot.banksystem.dtos.BankDTO;
import com.springboot.banksystem.services.AccountService;
import com.springboot.banksystem.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {
    @Autowired
    private BankService bankService;
    @Autowired
    private AccountService accountService;

    @PostMapping
    public BankDTO createBank(@RequestBody BankDTO bankDTO) {
        return bankService.createBank(bankDTO);
    }

    @GetMapping
    public List<BankDTO> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/{id}")
    public BankDTO getBankById(@PathVariable Long id) {
        return bankService.getBankById(id);
    }

    @GetMapping("/{id}/accounts")
    public List<AccountDTO> getAccountsByBankId(@PathVariable Long id) {
        return accountService.getAccountsByBankId(id);
    }

    @GetMapping("/{id}/total-transaction-fee")
    public double getBankTotalTransactionFeeAmount(@PathVariable Long id) {
        return bankService.getBankTotalTransactionFeeAmount(id);
    }

    @GetMapping("/{id}/total-transfer-amount")
    public double getBankTotalTransferAmount(@PathVariable Long id) {
        return bankService.getBankTotalTransferAmount(id);
    }
}