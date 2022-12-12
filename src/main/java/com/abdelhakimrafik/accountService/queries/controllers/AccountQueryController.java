package com.abdelhakimrafik.accountService.queries.controllers;

import com.abdelhakimrafik.accountService.queries.dtos.AccountResponseDTO;
import com.abdelhakimrafik.accountService.queries.dtos.AccountTransactionResponseDTO;
import com.abdelhakimrafik.accountService.queries.queries.AccountRequest;
import com.abdelhakimrafik.accountService.queries.queries.AllAccountsRequest;
import com.abdelhakimrafik.accountService.queries.queries.AllAccountTransactionsRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/accounts")
@NoArgsConstructor
@AllArgsConstructor
public class AccountQueryController {

    private QueryGateway queryGateway;

    @GetMapping("/")
    public CompletableFuture<List<AccountResponseDTO>> accountList() {
        return queryGateway.query(new AllAccountsRequest(), ResponseTypes.multipleInstancesOf(AccountResponseDTO.class));
    }

    @GetMapping("/{id}")
    public CompletableFuture<AccountResponseDTO> getAccountById(@PathVariable String id){
        return queryGateway.query(new AccountRequest(id), ResponseTypes.instanceOf(AccountResponseDTO.class));
    }

    @GetMapping("/transactions/{accountId}")
    public CompletableFuture<List<AccountTransactionResponseDTO>> accountTransactions(@PathVariable String accountId){
        return queryGateway.query(new AllAccountTransactionsRequest(accountId), ResponseTypes.multipleInstancesOf(AccountTransactionResponseDTO.class));
    }
}
