package com.abdelhakimrafik.accountService.queries.repositories;

import com.abdelhakimrafik.accountService.queries.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
