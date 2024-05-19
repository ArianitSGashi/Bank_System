package com.springboot.banksystem.dtos;

import java.util.List;

public class AccountDTO {
    private Long id;
    private String name;
    private double balance;
    private BankDTO bank;
    private List<TransactionDTO> originatingTransactions;
    private List<TransactionDTO> resultingTransactions;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public BankDTO getBank() {
        return bank;
    }

    public void setBank(BankDTO bank) {
        this.bank = bank;
    }

    public List<TransactionDTO> getOriginatingTransactions() {
        return originatingTransactions;
    }

    public void setOriginatingTransactions(List<TransactionDTO> originatingTransactions) {
        this.originatingTransactions = originatingTransactions;
    }

    public List<TransactionDTO> getResultingTransactions() {
        return resultingTransactions;
    }

    public void setResultingTransactions(List<TransactionDTO> resultingTransactions) {
        this.resultingTransactions = resultingTransactions;
    }
}
