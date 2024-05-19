package com.springboot.banksystem.repositorys;

import com.springboot.banksystem.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}