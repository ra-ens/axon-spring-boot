package com.abdelhakimrafik.accountService.commands.services;

import com.abdelhakimrafik.accountService.commonapi.dtos.CreateAccountRequestDTO;
import com.abdelhakimrafik.accountService.commonapi.dtos.CreditAccountRequestDTO;
import com.abdelhakimrafik.accountService.commonapi.dtos.DebitAccountRequestDTO;

import java.util.concurrent.CompletableFuture;

public interface IAccountCommandService {
    CompletableFuture<String> createAccount(CreateAccountRequestDTO accountRequestDTO);
    CompletableFuture<String> debitAccount(DebitAccountRequestDTO debitAccountRequestDTO);
    CompletableFuture<String> creditAccount(CreditAccountRequestDTO creditAccountRequestDTO);
}