package com.springboot.banksystem.services;

import com.springboot.banksystem.dtos.TransactionDTO;
import com.springboot.banksystem.exceptions.InsufficientFundsException;
import com.springboot.banksystem.exceptions.NotFoundException;
import com.springboot.banksystem.mappers.TransactionMapper;
import com.springboot.banksystem.models.Account;
import com.springboot.banksystem.models.Bank;
import com.springboot.banksystem.models.Transaction;
import com.springboot.banksystem.repositorys.TransactionRepository;
import com.springboot.banksystem.repositorys.AccountRepository;
import com.springboot.banksystem.repositorys.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankRepository bankRepository;

    public List<TransactionDTO> getTransactionsByAccountId(Long accountId) {
        List<Transaction> originatingTransactions = transactionRepository.findByOriginatingAccountId(accountId);
        List<Transaction> resultingTransactions = transactionRepository.findByResultingAccountId(accountId);

        return Stream.concat(originatingTransactions.stream(), resultingTransactions.stream())
                .map(transaction -> {
                    TransactionDTO transactionDTO = TransactionMapper.toTransactionDTO(transaction);
                    transactionDTO.setAccountId(accountId); // Ensure accountId is set
                    return transactionDTO;
                })
                .collect(Collectors.toList());
    }

    public TransactionDTO performTransaction(TransactionDTO transactionDTO) {
        Account originatingAccount = accountRepository.findById(transactionDTO.getOriginatingAccountId())
                .orElseThrow(() -> new NotFoundException("Originating account not found"));
        Account resultingAccount = accountRepository.findById(transactionDTO.getResultingAccountId())
                .orElseThrow(() -> new NotFoundException("Resulting account not found"));

        double fee = calculateTransactionFee(transactionDTO, originatingAccount.getBank());
        if (originatingAccount.getBalance() < transactionDTO.getAmount() + fee) {
            throw new InsufficientFundsException("Not enough funds");
        }

        originatingAccount.setBalance(originatingAccount.getBalance() - transactionDTO.getAmount() - fee);
        resultingAccount.setBalance(resultingAccount.getBalance() + transactionDTO.getAmount());

        Bank bank = originatingAccount.getBank();
        bank.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount() + fee);
        bank.setTotalTransferAmount(bank.getTotalTransferAmount() + transactionDTO.getAmount());

        accountRepository.save(originatingAccount);
        accountRepository.save(resultingAccount);
        bankRepository.save(bank);

        Transaction transaction = TransactionMapper.toTransaction(transactionDTO);
        transaction.setOriginatingAccount(originatingAccount);
        transaction.setResultingAccount(resultingAccount);
        transaction.setTransactionDate(new java.util.Date());
        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.toTransactionDTO(savedTransaction);
    }

    private double calculateTransactionFee(TransactionDTO transactionDTO, Bank bank) {
        double flatFee = bank.getTransactionFlatFeeAmount();
        double percentFee = bank.getTransactionPercentFeeValue() / 100 * transactionDTO.getAmount();
        return Math.max(flatFee, percentFee);
    }
}
