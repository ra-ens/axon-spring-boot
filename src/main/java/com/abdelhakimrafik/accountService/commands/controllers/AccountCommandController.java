package com.abdelhakimrafik.accountService.commands.controllers;

import com.abdelhakimrafik.accountService.commands.services.AccountCommandService;
import com.abdelhakimrafik.accountService.commonapi.dtos.CreateAccountRequestDTO;
import com.abdelhakimrafik.accountService.commonapi.dtos.CreditAccountRequestDTO;
import com.abdelhakimrafik.accountService.commonapi.dtos.DebitAccountRequestDTO;
import com.abdelhakimrafik.accountService.commonapi.exceptions.RequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/account")
@AllArgsConstructor
public class AccountCommandController {
    private final AccountCommandService accountCommandService;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO accountRequestDTO){
        return accountCommandService.createAccount(accountRequestDTO);
    }

    @PutMapping(path = "/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO debitAccountRequestDTO){
        return accountCommandService.debitAccount(debitAccountRequestDTO);
    }

    @PutMapping(path = "/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO creditAccountRequestDTO){
        return accountCommandService.creditAccount(creditAccountRequestDTO);
    }

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<String> negativeInitialBalanceExceptionHandler(RequestException exception){
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @GetMapping("/eventstore/{id}")
//    public Stream eventStore(@PathVariable String id){
//        return
//    }
}