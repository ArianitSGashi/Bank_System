package com.springboot.banksystem.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double balance = 0.0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "originatingAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> originatingTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "resultingAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> resultingTransactions = new ArrayList<>();

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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Transaction> getOriginatingTransactions() {
        return originatingTransactions;
    }

    public void setOriginatingTransactions(List<Transaction> originatingTransactions) {
        this.originatingTransactions = originatingTransactions;
    }

    public List<Transaction> getResultingTransactions() {
        return resultingTransactions;
    }

    public void setResultingTransactions(List<Transaction> resultingTransactions) {
        this.resultingTransactions = resultingTransactions;
    }
}

