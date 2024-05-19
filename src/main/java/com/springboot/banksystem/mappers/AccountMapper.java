package com.springboot.banksystem.mappers;

import com.springboot.banksystem.dtos.AccountDTO;
import com.springboot.banksystem.models.Account;
import org.hibernate.Hibernate;

import java.util.stream.Collectors;

public class AccountMapper {

    public static AccountDTO toAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setBank(BankMapper.toBankDTO(account.getBank()));

        Hibernate.initialize(account.getOriginatingTransactions());
        Hibernate.initialize(account.getResultingTransactions());

        accountDTO.setOriginatingTransactions(
                account.getOriginatingTransactions().stream()
                        .map(TransactionMapper::toTransactionDTO)
                        .collect(Collectors.toList())
        );
        accountDTO.setResultingTransactions(
                account.getResultingTransactions().stream()
                        .map(TransactionMapper::toTransactionDTO)
                        .collect(Collectors.toList())
        );
        return accountDTO;
    }

    public static Account toAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setName(accountDTO.getName());
        account.setBalance(accountDTO.getBalance());
        return account;
    }
}


