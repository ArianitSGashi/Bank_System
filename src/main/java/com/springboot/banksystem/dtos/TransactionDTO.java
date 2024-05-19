package com.springboot.banksystem.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {
    private Long id;
    private double amount;
    private Long originatingAccountId;
    private Long resultingAccountId;
    private String reason;
    private Date transactionDate;
    private Long accountId; // Add this field

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

    public Long getOriginatingAccountId() {
        return originatingAccountId;
    }

    public void setOriginatingAccountId(Long originatingAccountId) {
        this.originatingAccountId = originatingAccountId;
    }

    public Long getResultingAccountId() {
        return resultingAccountId;
    }

    public void setResultingAccountId(Long resultingAccountId) {
        this.resultingAccountId = resultingAccountId;
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
