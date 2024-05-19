package com.springboot.banksystem.mappers;

import com.springboot.banksystem.dtos.TransactionDTO;
import com.springboot.banksystem.models.Transaction;

public class TransactionMapper {

    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setOriginatingAccountId(transaction.getOriginatingAccount().getId());
        transactionDTO.setResultingAccountId(transaction.getResultingAccount().getId());
        transactionDTO.setReason(transaction.getReason());
        transactionDTO.setTransactionDate(transaction.getTransactionDate());
        return transactionDTO;
    }

    public static Transaction toTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDTO.getId());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setReason(transactionDTO.getReason());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());
        return transaction;
    }
}

