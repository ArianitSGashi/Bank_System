package com.springboot.banksystem;

import com.springboot.banksystem.dtos.AccountDTO;
import com.springboot.banksystem.dtos.BankDTO;
import com.springboot.banksystem.dtos.TransactionDTO;
import com.springboot.banksystem.services.AccountService;
import com.springboot.banksystem.services.BankService;
import com.springboot.banksystem.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TransactionServiceTest {

    @Autowired
    private BankService bankService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testPerformTransaction() {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName("Test Bank");
        bankDTO.setTransactionFlatFeeAmount(10);
        bankDTO.setTransactionPercentFeeValue(5);
        BankDTO createdBankDTO = bankService.createBank(bankDTO);

        AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setName("User1");
        accountDTO1.setBalance(100);
        accountDTO1.setBank(createdBankDTO);
        AccountDTO createdAccountDTO1 = accountService.createAccount(accountDTO1);

        AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setName("User2");
        accountDTO2.setBalance(50);
        accountDTO2.setBank(createdBankDTO);
        AccountDTO createdAccountDTO2 = accountService.createAccount(accountDTO2);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(20);
        transactionDTO.setOriginatingAccountId(createdAccountDTO1.getId());
        transactionDTO.setResultingAccountId(createdAccountDTO2.getId());
        transactionDTO.setReason("Test transfer");

        TransactionDTO createdTransactionDTO = transactionService.performTransaction(transactionDTO);
        assertNotNull(createdTransactionDTO.getId());
    }

    @Test
    public void testGetTransactionsByAccountId() {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName("Test Bank");
        bankDTO.setTransactionFlatFeeAmount(10);
        bankDTO.setTransactionPercentFeeValue(5);
        BankDTO createdBankDTO = bankService.createBank(bankDTO);

        AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setName("User1");
        accountDTO1.setBalance(100);
        accountDTO1.setBank(createdBankDTO);
        AccountDTO createdAccountDTO1 = accountService.createAccount(accountDTO1);

        AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setName("User2");
        accountDTO2.setBalance(50);
        accountDTO2.setBank(createdBankDTO);
        AccountDTO createdAccountDTO2 = accountService.createAccount(accountDTO2);

        TransactionDTO transactionDTO1 = new TransactionDTO();
        transactionDTO1.setAmount(20);
        transactionDTO1.setOriginatingAccountId(createdAccountDTO1.getId());
        transactionDTO1.setResultingAccountId(createdAccountDTO2.getId());
        transactionDTO1.setReason("Test transfer");
        transactionService.performTransaction(transactionDTO1);

        TransactionDTO transactionDTO2 = new TransactionDTO();
        transactionDTO2.setAmount(30);
        transactionDTO2.setOriginatingAccountId(createdAccountDTO2.getId());
        transactionDTO2.setResultingAccountId(createdAccountDTO1.getId());
        transactionDTO2.setReason("Test transfer");
        transactionService.performTransaction(transactionDTO2);

        List<TransactionDTO> transactions = transactionService.getTransactionsByAccountId(createdAccountDTO1.getId());
        assertEquals(2, transactions.size());
    }
}
