package com.springboot.banksystem.services;

import com.springboot.banksystem.dtos.BankDTO;
import com.springboot.banksystem.exceptions.NotFoundException;
import com.springboot.banksystem.mappers.BankMapper;
import com.springboot.banksystem.models.Bank;
import com.springboot.banksystem.repositorys.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public BankDTO createBank(BankDTO bankDTO) {
        Bank bank = BankMapper.toBank(bankDTO);
        Bank savedBank = bankRepository.save(bank);
        return BankMapper.toBankDTO(savedBank);
    }

    public List<BankDTO> getAllBanks() {
        return bankRepository.findAll().stream()
                .map(BankMapper::toBankDTO)
                .collect(Collectors.toList());
    }

    public BankDTO getBankById(Long id) {
        return bankRepository.findById(id)
                .map(BankMapper::toBankDTO)
                .orElseThrow(() -> new NotFoundException("Bank not found"));
    }

    public double getBankTotalTransactionFeeAmount(Long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new NotFoundException("Bank not found"));
        return bank.getTotalTransactionFeeAmount();
    }

    public double getBankTotalTransferAmount(Long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new NotFoundException("Bank not found"));
        return bank.getTotalTransferAmount();
    }
}

