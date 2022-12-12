package com.abdelhakimrafik.accountService.queries.repositories;

import com.abdelhakimrafik.accountService.queries.entities.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<AccountTransaction, Long> {
    List<AccountTransaction> findByAccount_Id(String accountId);
}
