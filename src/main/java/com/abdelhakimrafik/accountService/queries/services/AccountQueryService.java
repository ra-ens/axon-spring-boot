package com.abdelhakimrafik.accountService.queries.services;

import com.abdelhakimrafik.accountService.commonapi.enums.TransationType;
import com.abdelhakimrafik.accountService.commonapi.events.AccountCreatedEvent;
import com.abdelhakimrafik.accountService.commonapi.events.AccountCreditedEvent;
import com.abdelhakimrafik.accountService.commonapi.events.AccountDebitedEvent;
import com.abdelhakimrafik.accountService.queries.dtos.*;
import com.abdelhakimrafik.accountService.queries.entities.Account;
import com.abdelhakimrafik.accountService.queries.entities.AccountTransaction;
import com.abdelhakimrafik.accountService.queries.mappers.AccountMapper;
import com.abdelhakimrafik.accountService.queries.queries.AccountRequest;
import com.abdelhakimrafik.accountService.queries.queries.AllAccountTransactionsRequest;
import com.abdelhakimrafik.accountService.queries.queries.AllAccountsRequest;
import com.abdelhakimrafik.accountService.queries.repositories.AccountRepository;
import com.abdelhakimrafik.accountService.queries.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class AccountQueryService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private AccountMapper accountMapper;

    @EventHandler
    public void on(AccountCreatedEvent event, EventMessage<AccountCreatedEvent> eventMessage) {
        Account account = Account.builder()
                .id(event.getId())
                .currency(event.getCurrency())
                .balance(event.getBalance())
                .status(event.getStatus())
                .build();
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountDebitedEvent event){
        Account account = accountRepository.findById(event.getId()).get();
        account.setBalance(account.getBalance() - event.getAmount());
        Account savedAccount = accountRepository.save(account);
        AccountTransaction accountTransaction = AccountTransaction.builder()
                .account(account)
                .amount(event.getAmount())
                .operationDate(new Date())
                .type(TransationType.DEBIT)
                .build();
        transactionRepository.save(accountTransaction);
    }

    @EventHandler
    public void on(AccountCreditedEvent event){
        Account account = accountRepository.findById(event.getId()).get();
        account.setBalance(account.getBalance() + event.getAmount());
        Account savedAccount = accountRepository.save(account);
        AccountTransaction accountTransaction = AccountTransaction.builder()
                .account(account)
                .amount(event.getAmount())
                .operationDate(new Date())
                .type(TransationType.CREDIT)
                .build();
        transactionRepository.save(accountTransaction);
    }

    @QueryHandler
    public AccountResponseDTO on(AccountRequest request) {
        Account bankAccount = accountRepository.findById(request.getId()).get();
        return accountMapper.accountToAccountResponseDTO(bankAccount);
    }

    @QueryHandler
    public List<AccountResponseDTO> on(AllAccountsRequest query) {
        List<Account> accounts = accountRepository.findAll();
        return accounts
                .stream()
                .map((account -> accountMapper.accountToAccountResponseDTO(account)))
                .collect(Collectors.toList());
    }

    @QueryHandler
    public List<AccountTransactionResponseDTO> on(AllAccountTransactionsRequest request) {
        List<AccountTransaction> accountTransactions = transactionRepository.findByAccount_Id(request.getAccountId());
        return accountTransactions
                .stream()
                .map(transaction -> accountMapper.accountTransactionToAccountTransactionResponseDTO(transaction))
                .collect(Collectors.toList());
    }
}
