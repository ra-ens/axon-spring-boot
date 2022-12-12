package com.abdelhakimrafik.accountService.queries.mappers;

import com.abdelhakimrafik.accountService.queries.dtos.AccountResponseDTO;
import com.abdelhakimrafik.accountService.queries.dtos.AccountTransactionResponseDTO;
import com.abdelhakimrafik.accountService.queries.entities.Account;
import com.abdelhakimrafik.accountService.queries.entities.AccountTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountResponseDTO accountToAccountResponseDTO(Account account);
    AccountTransactionResponseDTO accountTransactionToAccountTransactionResponseDTO(AccountTransaction accountTransaction);
}
