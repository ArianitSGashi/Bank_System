package com.springboot.banksystem;

import com.springboot.banksystem.dtos.AccountDTO;
import com.springboot.banksystem.dtos.BankDTO;
import com.springboot.banksystem.dtos.TransactionDTO;
import com.springboot.banksystem.exceptions.InsufficientFundsException;
import com.springboot.banksystem.exceptions.NotFoundException;
import com.springboot.banksystem.services.AccountService;
import com.springboot.banksystem.services.BankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AccountServiceTest {

    @Autowired
    private BankService bankService;

    @Autowired
    private AccountService accountService;

    @Test
    public void testCreateAccount() {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName("Test Bank");
        bankDTO.setTransactionFlatFeeAmount(10);
        bankDTO.setTransactionPercentFeeValue(5);
        BankDTO createdBankDTO = bankService.createBank(bankDTO);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("Test User");
        accountDTO.setBalance(100);
        accountDTO.setBank(createdBankDTO);

        AccountDTO createdAccountDTO = accountService.createAccount(accountDTO);
        assertNotNull(createdAccountDTO.getId());
        assertEquals("Test User", createdAccountDTO.getName());
    }

    @Test
    public void testWithdraw() {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName("Test Bank");
        bankDTO.setTransactionFlatFeeAmount(10);
        bankDTO.setTransactionPercentFeeValue(5);
        BankDTO createdBankDTO = bankService.createBank(bankDTO);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("Test User");
        accountDTO.setBalance(100);
        accountDTO.setBank(createdBankDTO);
        AccountDTO createdAccountDTO = accountService.createAccount(accountDTO);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountId(createdAccountDTO.getId());
        transactionDTO.setAmount(20);

        AccountDTO updatedAccountDTO = accountService.withdraw(transactionDTO);
        assertEquals(80, updatedAccountDTO.getBalance());
    }

    @Test
    public void testDeposit() {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName("Test Bank");
        bankDTO.setTransactionFlatFeeAmount(10);
        bankDTO.setTransactionPercentFeeValue(5);
        BankDTO createdBankDTO = bankService.createBank(bankDTO);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("Test User");
        accountDTO.setBalance(100);
        accountDTO.setBank(createdBankDTO);
        AccountDTO createdAccountDTO = accountService.createAccount(accountDTO);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountId(createdAccountDTO.getId());
        transactionDTO.setAmount(50);

        AccountDTO updatedAccountDTO = accountService.deposit(transactionDTO);
        assertEquals(150, updatedAccountDTO.getBalance());
    }

    @Test
    public void testGetAllAccounts() {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName("Test Bank");
        bankDTO.setTransactionFlatFeeAmount(10);
        bankDTO.setTransactionPercentFeeValue(5);
        BankDTO createdBankDTO = bankService.createBank(bankDTO);

        AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setName("User1");
        accountDTO1.setBalance(100);
        accountDTO1.setBank(createdBankDTO);
        accountService.createAccount(accountDTO1);

        AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setName("User2");
        accountDTO2.setBalance(50);
        accountDTO2.setBank(createdBankDTO);
        accountService.createAccount(accountDTO2);

        List<AccountDTO> accounts = accountService.getAllAccounts();
        assertEquals(2, accounts.size());
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName("Test Bank");
        bankDTO.setTransactionFlatFeeAmount(10);
        bankDTO.setTransactionPercentFeeValue(5);
        BankDTO createdBankDTO = bankService.createBank(bankDTO);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("Test User");
        accountDTO.setBalance(10);
        accountDTO.setBank(createdBankDTO);
        AccountDTO createdAccountDTO = accountService.createAccount(accountDTO);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountId(createdAccountDTO.getId());
        transactionDTO.setAmount(20);

        Exception exception = assertThrows(InsufficientFundsException.class, () -> {
            accountService.withdraw(transactionDTO);
        });

        String expectedMessage = "Not enough funds";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAccountNotFoundException() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountId(999L); // Non-existing account ID
        transactionDTO.setAmount(20);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            accountService.withdraw(transactionDTO);
        });

        String expectedMessage = "Account not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}


