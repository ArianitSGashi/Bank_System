package com.springboot.banksystem;

import com.springboot.banksystem.dtos.BankDTO;
import com.springboot.banksystem.services.BankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BankServiceTest {

    @Autowired
    private BankService bankService;

    @Test
    public void testCreateBank() {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName("Test Bank");
        bankDTO.setTransactionFlatFeeAmount(10);
        bankDTO.setTransactionPercentFeeValue(5);

        BankDTO createdBankDTO = bankService.createBank(bankDTO);
        assertNotNull(createdBankDTO.getId());
        assertEquals("Test Bank", createdBankDTO.getName());
    }

    @Test
    public void testGetBankTotalTransactionFeeAmount() {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName("Test Bank");
        bankDTO.setTransactionFlatFeeAmount(10);
        bankDTO.setTransactionPercentFeeValue(5);

        BankDTO createdBankDTO = bankService.createBank(bankDTO);
        double totalTransactionFeeAmount = bankService.getBankTotalTransactionFeeAmount(createdBankDTO.getId());
        assertEquals(0, totalTransactionFeeAmount);
    }

    @Test
    public void testGetBankTotalTransferAmount() {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName("Test Bank");
        bankDTO.setTransactionFlatFeeAmount(10);
        bankDTO.setTransactionPercentFeeValue(5);

        BankDTO createdBankDTO = bankService.createBank(bankDTO);
        double totalTransferAmount = bankService.getBankTotalTransferAmount(createdBankDTO.getId());
        assertEquals(0, totalTransferAmount);
    }

    @Test
    public void testGetBankById() {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName("Test Bank");
        bankDTO.setTransactionFlatFeeAmount(10);
        bankDTO.setTransactionPercentFeeValue(5);

        BankDTO createdBankDTO = bankService.createBank(bankDTO);
        BankDTO fetchedBankDTO = bankService.getBankById(createdBankDTO.getId());
        assertNotNull(fetchedBankDTO);
        assertEquals("Test Bank", fetchedBankDTO.getName());
    }
}

