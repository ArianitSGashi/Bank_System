package com.springboot.banksystem.repositorys;

import com.springboot.banksystem.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
