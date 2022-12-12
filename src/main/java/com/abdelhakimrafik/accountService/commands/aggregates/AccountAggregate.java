package com.abdelhakimrafik.accountService.commands.aggregates;

import com.abdelhakimrafik.accountService.commonapi.commands.CreateAccountCommand;
import com.abdelhakimrafik.accountService.commonapi.commands.CreditAccountCommand;
import com.abdelhakimrafik.accountService.commonapi.commands.DebitAccountCommand;
import com.abdelhakimrafik.accountService.commonapi.enums.AccountStatus;
import com.abdelhakimrafik.accountService.commonapi.events.AccountCreatedEvent;
import com.abdelhakimrafik.accountService.commonapi.events.AccountCreditedEvent;
import com.abdelhakimrafik.accountService.commonapi.events.AccountDebitedEvent;
import com.abdelhakimrafik.accountService.commonapi.exceptions.NegativeAmountException;
import com.abdelhakimrafik.accountService.commonapi.exceptions.NegativeInitialBalanceException;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private String currency;
    private double balance;
    private AccountStatus status;

    public AccountAggregate(){} // required by Axon

    @CommandHandler
    public void AccountAggregate(CreateAccountCommand command) throws NegativeInitialBalanceException {
        if(command.getInitialBalance() < 0) throw new NegativeInitialBalanceException("Negative initial balance");
        log.info("New CreateAccountCommand: " + command.getId());
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getCurrency(),
                command.getInitialBalance(),
                AccountStatus.CREATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        log.info("AccountCreatedEvent: " + event.getId());
        this.accountId = event.getId();
        this.currency = event.getCurrency();
        this.balance = event.getBalance();
        this.status = event.getStatus();
    }

    @CommandHandler
    public void creditAccountHandler(CreditAccountCommand command) throws NegativeAmountException {
        if(command.getAmount() < 0) throw new NegativeAmountException("Negative amount");
        log.info("CreditAccountCommand: " + command.getId() + ", Amount: " + command.getAmount());
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getCurrency(),
                command.getAmount()
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event) {
        log.info("AccountCreditedEvent: " + event.getId() + ", Amount: " + event.getAmount());
        this.balance += event.getAmount();
    }

    @CommandHandler
    public void debitAccountHandler(DebitAccountCommand command) throws NegativeAmountException {
        if(command.getAmount() < 0) throw new NegativeAmountException("Negative amount");
        log.info("DebitAccountCommand: " + command.getId() + ", Amount: " + command.getAmount());
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getId(),
                command.getCurrency(),
                command.getAmount()
        ));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event) {
        log.info("AccountDebitedEvent: " + event.getId() + ", Amount: " + event.getAmount());
        this.balance -= event.getAmount();
    }
}
