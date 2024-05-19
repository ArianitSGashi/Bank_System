package com.springboot.banksystem.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private String reason;
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "originating_account_id")
    private Account originatingAccount;

    @ManyToOne
    @JoinColumn(name = "resulting_account_id")
    private Account resultingAccount;

    private double feeAmount; // Add this field

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Account getOriginatingAccount() {
        return originatingAccount;
    }

    public void setOriginatingAccount(Account originatingAccount) {
        this.originatingAccount = originatingAccount;
    }

    public Account getResultingAccount() {
        return resultingAccount;
    }

    public void setResultingAccount(Account resultingAccount) {
        this.resultingAccount = resultingAccount;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }
}
