package com.abdelhakimrafik.accountService.commands.services;

import com.abdelhakimrafik.accountService.commonapi.commands.CreateAccountCommand;
import com.abdelhakimrafik.accountService.commonapi.commands.CreditAccountCommand;
import com.abdelhakimrafik.accountService.commonapi.commands.DebitAccountCommand;
import com.abdelhakimrafik.accountService.commonapi.dtos.CreateAccountRequestDTO;
import com.abdelhakimrafik.accountService.commonapi.dtos.CreditAccountRequestDTO;
import com.abdelhakimrafik.accountService.commonapi.dtos.DebitAccountRequestDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AccountCommandService implements IAccountCommandService{
    private CommandGateway commandGateway;
    @Override
    public CompletableFuture<String> createAccount(CreateAccountRequestDTO request) {
        return commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.getCurrency(),
                request.getInitialBalance()
        ));
    }

    @Override
    public CompletableFuture<String> debitAccount(DebitAccountRequestDTO request) {
        return commandGateway.send(new DebitAccountCommand(
                request.getAccountId(),
                request.getCurrency(),
                request.getAmount()
        ));
    }

    @Override
    public CompletableFuture<String> creditAccount(CreditAccountRequestDTO request) {
        return commandGateway.send(new CreditAccountCommand(
                request.getAccountId(),
                request.getCurrency(),
                request.getAmount()
        ));
    }
}
