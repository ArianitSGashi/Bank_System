package com.springboot.banksystem.mappers;

import com.springboot.banksystem.dtos.BankDTO;
import com.springboot.banksystem.models.Bank;

public class BankMapper {

    public static BankDTO toBankDTO(Bank bank) {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setId(bank.getId());
        bankDTO.setName(bank.getName());
        bankDTO.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount());
        bankDTO.setTotalTransferAmount(bank.getTotalTransferAmount());
        bankDTO.setTransactionFlatFeeAmount(bank.getTransactionFlatFeeAmount());
        bankDTO.setTransactionPercentFeeValue(bank.getTransactionPercentFeeValue());
        return bankDTO;
    }

    public static Bank toBank(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setId(bankDTO.getId());
        bank.setName(bankDTO.getName());
        bank.setTotalTransactionFeeAmount(bankDTO.getTotalTransactionFeeAmount());
        bank.setTotalTransferAmount(bankDTO.getTotalTransferAmount());
        bank.setTransactionFlatFeeAmount(bankDTO.getTransactionFlatFeeAmount());
        bank.setTransactionPercentFeeValue(bankDTO.getTransactionPercentFeeValue());
        return bank;
    }
}
