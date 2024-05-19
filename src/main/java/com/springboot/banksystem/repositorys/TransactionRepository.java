package com.springboot.banksystem.repositorys;

import com.springboot.banksystem.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByOriginatingAccountId(Long accountId);
    List<Transaction> findByResultingAccountId(Long accountId);
}

