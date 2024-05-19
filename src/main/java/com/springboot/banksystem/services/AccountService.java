package com.springboot.banksystem.services;

import com.springboot.banksystem.dtos.AccountDTO;
import com.springboot.banksystem.dtos.TransactionDTO;
import com.springboot.banksystem.exceptions.InsufficientFundsException;
import com.springboot.banksystem.exceptions.NotFoundException;
import com.springboot.banksystem.mappers.AccountMapper;
import com.springboot.banksystem.models.Account;
import com.springboot.banksystem.models.Bank;
import com.springboot.banksystem.repositorys.AccountRepository;
import com.springboot.banksystem.repositorys.BankRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankRepository bankRepository;

    public AccountDTO createAccount(AccountDTO accountDTO) {
        Bank bank = bankRepository.findById(accountDTO.getBank().getId())
                .orElseThrow(() -> new NotFoundException("Bank not found"));
        Account account = AccountMapper.toAccount(accountDTO);
        account.setBank(bank);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toAccountDTO(savedAccount);
    }

    @Transactional
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::toAccountDTO).collect(Collectors.toList());
    }

    @Transactional
    public AccountDTO getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(AccountMapper::toAccountDTO)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public List<AccountDTO> getAccountsByBankId(Long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new NotFoundException("Bank not found"));
        return bank.getAccounts().stream()
                .map(AccountMapper::toAccountDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AccountDTO withdraw(TransactionDTO transactionDTO) {
        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new NotFoundException("Account not found"));
        if (account.getBalance() < transactionDTO.getAmount()) {
            throw new InsufficientFundsException("Not enough funds");
        }
        account.setBalance(account.getBalance() - transactionDTO.getAmount());
        accountRepository.save(account);
        return AccountMapper.toAccountDTO(account);
    }

    @Transactional
    public AccountDTO deposit(TransactionDTO transactionDTO) {
        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new NotFoundException("Account not found"));
        account.setBalance(account.getBalance() + transactionDTO.getAmount());
        accountRepository.save(account);
        return AccountMapper.toAccountDTO(account);
    }
}

